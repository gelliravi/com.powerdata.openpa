package com.powerdata.openpa.psse.csv;

import com.powerdata.openpa.psse.Bus;
import com.powerdata.openpa.psse.PsseModelException;
import com.powerdata.openpa.tools.ComplexList;

public class PhaseShifterList extends com.powerdata.openpa.psse.PhaseShifterList
{
	int _size;

	/* line 1 */
	private String[] _ckt, _name;
	private int[] _i, _j, _cw, _cm, _nmetr, _stat;
	private float[] _mag1, _mag2;
	
	/* line 2 */
	private ComplexList _z;
	private float[] _sbase;

	/* line 3 */
	private float[]		_windv1, _nomv1, _ang1, _rata1, _ratb1, _ratc1, _rma1,
			_rmi1, _vma1, _vmi1, _cr1, _cx1;
	private int[]		_cod1, _ntp1, _tab1;
	
	public PhaseShifterList(PsseModel model, TransformerRawList rlist,
			TransformerPrep prep) throws PsseModelException
	{
		super(model);

		_size = prep.size();
		
		int[] xfndx = prep.getXfRaw(), wndx = prep.getWndx(); 
		_i = prep.getBusI();
		_j = prep.getBusJ();
		_z = prep.getZ();
		
		_ckt = (String[]) TransformerRawList.loadArray(rlist, xfndx, "CKT", String.class);
		_name = (String[]) TransformerRawList.loadArray(rlist, xfndx, "NAME", String.class);
		_cw = (int[]) TransformerRawList.loadArray(rlist, xfndx, "CW", int.class);
		_cm = (int[]) TransformerRawList.loadArray(rlist, xfndx, "CM", int.class);
		_nmetr = TransformerRawList.loadNmetr(rlist, xfndx, wndx);
		_stat = TransformerRawList.loadStat(rlist, xfndx, wndx);
		float[][] tmag = TransformerRawList.loadMag(rlist, xfndx, wndx);
		_mag1 = tmag[0];
		_mag2 = tmag[1];
		_sbase = TransformerRawList.loadSbase(rlist, xfndx, wndx);

		_windv1 = (float[]) new WndLoader("WINDV").load(rlist, xfndx, wndx, float.class);
		_nomv1 = (float[]) new WndLoader("NOMV").load(rlist, xfndx, wndx, float.class);
		_ang1 = (float[]) new WndLoader("ANG").load(rlist, xfndx, wndx, float.class);
		_rata1 = (float[]) new WndLoader("RATA").load(rlist, xfndx, wndx, float.class);
		_ratb1 = (float[]) new WndLoader("RATB").load(rlist, xfndx, wndx, float.class);
		_ratc1 = (float[]) new WndLoader("RATC").load(rlist, xfndx, wndx, float.class);
		_cod1 = (int[]) new WndLoader("COD").load(rlist, xfndx, wndx, int.class);
		_rma1 = (float[]) new WndLoader("RMA").load(rlist, xfndx, wndx, float.class);
		_rmi1 = (float[]) new WndLoader("RMI").load(rlist, xfndx, wndx, float.class);
		_vma1 = (float[]) new WndLoader("VMA").load(rlist, xfndx, wndx, float.class);
		_vmi1 = (float[]) new WndLoader("VMI").load(rlist, xfndx, wndx, float.class);
		_ntp1 = (int[]) new WndLoader("NTP").load(rlist, xfndx, wndx, int.class);
		_tab1 = (int[]) new WndLoader("TAB").load(rlist, xfndx, wndx, int.class);
		_cr1 = (float[]) new WndLoader("CR").load(rlist, xfndx, wndx, float.class);
		_cx1 = (float[]) new WndLoader("CX").load(rlist, xfndx, wndx, float.class);
		
	}

	@Override
	public Bus getFromBus(int ndx) throws PsseModelException {return _buses.get(_i[ndx]);}
	@Override
	public Bus getToBus(int ndx) throws PsseModelException {return _buses.get(_j[ndx]);}

	@Override
	public String getI(int ndx) throws PsseModelException {return _buses.get(_i[ndx]).getObjectID();}
	@Override
	public String getJ(int ndx) throws PsseModelException {return _buses.get(_j[ndx]).getObjectID();}

	@Override
	public String getCKT(int ndx) throws PsseModelException {return _ckt[ndx];}
	@Override
	public int getCW(int ndx) throws PsseModelException {return _cw[ndx];}
	@Override
	public int getCM(int ndx) throws PsseModelException {return _cm[ndx];}
	@Override
	public float getMAG1(int ndx) throws PsseModelException {return _mag1[ndx];}
	@Override
	public float getMAG2(int ndx) throws PsseModelException {return _mag2[ndx];}
	@Override
	public int getNMETR(int ndx) throws PsseModelException {return _nmetr[ndx];}
	@Override
	public String getNAME(int ndx) throws PsseModelException {return _name[ndx];}
	@Override
	public int getSTAT(int ndx) throws PsseModelException {return _stat[ndx];}

	@Override
	public float getR1_2(int ndx) throws PsseModelException {return _z.re(ndx);}
	@Override
	public float getX1_2(int ndx) throws PsseModelException {return _z.im(ndx);}
	@Override
	public float getSBASE1_2(int ndx) throws PsseModelException {return _sbase[ndx];}

	@Override
	public float getWINDV1(int ndx) throws PsseModelException {return _windv1[ndx];}
	@Override
	public float getNOMV1(int ndx) throws PsseModelException {return _nomv1[ndx];}
	@Override
	public float getANG1(int ndx) throws PsseModelException {return _ang1[ndx];}
	@Override
	public float getRATA1(int ndx) throws PsseModelException {return _rata1[ndx];}
	@Override
	public float getRATB1(int ndx) throws PsseModelException {return _ratb1[ndx];}
	@Override
	public float getRATC1(int ndx) throws PsseModelException {return _ratc1[ndx];}
	@Override
	public int getCOD1(int ndx) throws PsseModelException {return _cod1[ndx];}
	@Override
	public float getRMA1(int ndx) throws PsseModelException {return _rma1[ndx];}
	@Override
	public float getRMI1(int ndx) throws PsseModelException {return _rmi1[ndx];}
	@Override
	public float getVMA1(int ndx) throws PsseModelException {return _vma1[ndx];}
	@Override
	public float getVMI1(int ndx) throws PsseModelException {return _vmi1[ndx];}
	@Override
	public int getNTP1(int ndx) throws PsseModelException {return _ntp1[ndx];}
	@Override
	public int getTAB1(int ndx) throws PsseModelException {return _tab1[ndx];}
	@Override
	public float getCR1(int ndx) throws PsseModelException {return _cr1[ndx];}
	@Override
	public float getCX1(int ndx) throws PsseModelException {return _cx1[ndx];}
	
	
	@Override
	public String getObjectID(int ndx) throws PsseModelException
	{
		StringBuilder sb = new StringBuilder(getI(ndx));
		sb.append('-');
		sb.append(getJ(ndx));
		sb.append('-');
		sb.append(getCKT(ndx));
		return sb.toString();
	}


	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}