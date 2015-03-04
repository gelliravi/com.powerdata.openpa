package com.powerdata.openpa.pwrflow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import com.powerdata.openpa.ACBranch;
import com.powerdata.openpa.ACBranchList;
import com.powerdata.openpa.Bus;
import com.powerdata.openpa.BusList;
import com.powerdata.openpa.OneTermDev;
import com.powerdata.openpa.OneTermDevList;
import com.powerdata.openpa.PAModel;
import com.powerdata.openpa.PAModelException;
import com.powerdata.openpa.TwoTermDCLine;
import com.powerdata.openpa.tools.PAMath;

public class DetailMismatchReporter implements MismatchReporter
{
	File _dir;
	BusList _buses;
	PAModel _m;
	String _pad;
	PrintWriter _pw;
	int _iter = 0;
	
	public DetailMismatchReporter(PAModel model, File dir)
	{
		_dir = dir;
		_m = model;
	}
	
	@Override
	public MismatchReporter reportBegin(BusList buses)
	{
		_buses = buses;
		return this;
	}

	@Override
	public void reportMismatch(float[] pmm, float[] qmm, float[] vm, float[] va, BusType[] type) throws PAModelException
	{
		++_iter;
		try
		{
			boolean usesta = !_m.getStations().isEmpty();
			_pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(
					_dir, String.format("mismatches-%d.csv", _iter)))));
			_pw.print("\"BusID\",\"Area\",");
			if (usesta) _pw.print("\"Station\",");
			_pw.println("\"BusName\",\"KV\",\"VM\", \"VA\",\"Pmm\",\"Qmm\",\"DevType\",\"DevName\",\"MW\",\"MVAr\",\"Tap\"");
			StringBuilder bpad = new StringBuilder();
			for (int i = 0; i < (usesta ? 9 : 8); ++i)
				bpad.append("\"\",");
			_pad = bpad.toString();
			for (Bus b : _buses)
			{
				_pw.format("\"%s\",\"%s\",", b.getID(), b.getArea().getName());
				if (usesta) _pw.format("\"%s\",", b.getStation().getName());
				int idx = b.getIndex();
				_pw.format("\"%s\",%f,%f,%f,%f,%f\n", b.getName(), b
						.getVoltageLevel().getBaseKV(), vm[idx], PAMath.rad2deg(va[idx]),
					pmm[idx], qmm[idx]);
				reportACBranches(b);
				report2TDCLines(b);
				reportOneTermDevs(b);
			}
			_pw.close();
		}
		catch (IOException ex)
		{
			throw new PAModelException(ex);
		}
		_pw = null;		
	}
	
	

	@Override
	public void reportEnd()
	{
		// do nothing
	}
	
	private void report2TDCLines(Bus b) throws PAModelException
	{
		for (TwoTermDCLine dev : b.getTwoTermDCLines())
		{
			float mw, mvar;
			if (b.equals(_buses.getByBus(dev.getFromBus())))
			{
				mw = dev.getFromP();
				mvar = dev.getFromQ();
			}
			else
			{
				mw = dev.getToP();
				mvar = dev.getToQ();
			}
			_pw.format("%s\"%s\",\"%s\",%f,%f\n", _pad, dev.getList()
					.getListMeta().toString(), dev.getName(), mw, mvar);
		}
	}
	private void reportACBranches(Bus b) throws PAModelException
	{
		for (ACBranchList devlist : b.getACBranches())
		{
			for (ACBranch dev : devlist)
			{
				float mw, mvar, tap = 0f;
				if (b.equals(_buses.getByBus(dev.getFromBus())))
				{
					mw = dev.getFromP();
					mvar = dev.getFromQ();
					tap = dev.getFromTap();
				}
				else
				{
					mw = dev.getToP();
					mvar = dev.getToQ();
					tap = dev.getToTap();
				}
				_pw.format("%s\"%s\",\"%s\",%f,%f,%f\n", _pad, dev.getList()
						.getListMeta().toString(), dev.getName(), mw, mvar, tap);
			}
		}
	}
	


	private void reportOneTermDevs(Bus b) throws PAModelException
	{
		for (OneTermDevList devlist : b.getOneTermDevices())
		{
			for (OneTermDev dev : devlist)
			{
				_pw.format("%s\"%s\",\"%s\",%f,%f\n", _pad, dev.getList()
						.getListMeta().toString(), dev.getName(), dev.getP(),
					dev.getQ());
			}
		}
	}
	

}
