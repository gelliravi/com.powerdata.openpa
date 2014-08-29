package com.powerdata.openpa.tools;

import java.io.PrintWriter;
import java.util.Arrays;
import com.powerdata.openpa.tools.SpSymMtrxFactPattern.EliminatedBus;

/**
 * Sparse Symmetric susceptance matrix
 * 
 * @author chris@powerdata.com
 * 
 */
public class SpSymBMatrix
{
	protected class Factorizer extends SpSymMtrxFactorizer
	{
		Factorizer(float[] bdiag, float[] boffdiag)
		{
			bd = bdiag.clone();
			/*
			 * Note that ensureCapacity is called prior to any writes, so
			 * boffdiag does not get modified
			 */
			bo = boffdiag;
		}

		float[]	bd, bo, temp;

		@Override
		protected void elimStart(int elimbus, int[] cbus, int[] cbr)
		{
			int nmut = cbus.length;
			temp = new float[nmut];
			for (int i = 0; i < nmut; ++i)
			{
				float boelim = bo[cbr[i]];
				temp[i] = -boelim / bd[elimbus];
				bd[cbus[i]] += temp[i] * boelim;
			}
		}

		@Override
		protected void mutual(int imut, int adjbr, int targbr)
		{
			if (targbr != -1) 
				bo[targbr] += temp[imut] * bo[adjbr];
		}

		@Override
		protected int ensureCapacity(int newsize)
		{
			newsize = super.ensureCapacity(newsize);
			if (bo.length <= newsize)
				bo = Arrays.copyOf(bo, newsize);
			return newsize;
		}

		public float[] getBDiag()
		{
			return bd;
		}

		public float[] getBOffDiag()
		{
			return bo;
		}

		@Override
		protected void setup(LinkNet matrix)
		{
			// do nothing
		}

		@Override
		protected void finish()
		{
			// do nothing
		}

		@Override
		protected void elimStop()
		{
			// do nothing
		}
	}

	protected float[] _bdiag, _boffdiag;
	protected int[][] _save;
	protected LinkNet _net;
	
	public SpSymBMatrix(LinkNet net, float[] bdiag, float[] boffdiag, int[] ... save)
	{
		_save = save;
		_net = net;
		_bdiag = bdiag.clone();
		_boffdiag = boffdiag.clone();
	}

	public FactorizedBMatrix factorize()
	{
		Factorizer f = new Factorizer(_bdiag, _boffdiag);
		f.eliminate(_net, _save);
		return new FactorizedBMatrix(f.getBDiag(), f.getBOffDiag(),
				f.getElimFromNode(), f.getElimToNode(), f.getElimNdOrder(),
				f.getElimNdCount(), f.getElimBrOrder(), f.getElimBranchCount());
	}
	
	/** modify susceptance entry */
	public void incB(int f, int t, float b)
	{
		if (f == t)
			_bdiag[f] += b;
		else
			_boffdiag[_net.findBranch(f, t)] += b;
	}
	
	/** modify susceptence entry on the diagonal */
	public void incBdiag(int bus, float b)
	{
		_bdiag[bus] += b;
	}
	
	/** modify susceptance for an off-diagonal entry */
	public void incBoffdiag(int br, float b)
	{
		_boffdiag[br] += b;
	}

	/**
	 * Create a factorized susceptance matrix using a saved pattern
	 * 
	 * Neither the pattern nor susceptance arrays are modified
	 * 
	 * @param pattern
	 *            Existing factorizer
	 * @param bdiag
	 *            diagonal susceptance values (array with size equal to number
	 *            of buses)
	 * @param boffdiag
	 *            off-diagonal susceptance values (array with size equal to
	 *            number of branches)
	 * 
	 * @return factorized susceptance matrix
	 * 
	 */
	public FactorizedBMatrix factorize(SpSymMtrxFactPattern pattern)
	{
		Factorizer f = new Factorizer(_bdiag, _boffdiag);
		f.ensureCapacity(pattern.getElimBranchCount());
		/* fake out the factorize, but use the same equations for B */
		for (EliminatedBus ebusr : pattern.getEliminatedBuses())
		{
			int[] cbus = ebusr.getRemainingNodes();
			int[] cbr = ebusr.getElimBranches();
			f.elimStart(ebusr.getElimBusNdx(), cbus, cbr);
			int[] tbr = ebusr.getRemainingBranches();
			int nmut = cbus.length, imut = 0;
			for (int i = 0; i < nmut; ++i)
			{
				for (int j = i + 1; j < nmut; ++j)
					f.mutual(i, cbr[j], tbr[imut++]);
			}
		}
		return new FactorizedBMatrix(f.getBDiag(), f.getBOffDiag(),
				pattern.getElimFromNode(), pattern.getElimToNode(),
				pattern.getElimNdOrder(), pattern.getElimNdCount(),
				pattern.getElimBrOrder(), pattern.getElimBranchCount());
	}

	public void dump(String[] name, PrintWriter pw)
	{
		pw.println("Bus,Bself,Far,Btran");
		int nbus = _bdiag.length;
		for(int i=0; i < nbus; ++i)
		{
			if(_net.getConnectionCount(i) > 0)
			{
				int[][] conn = _net.findConnections(i);
				int[] nd = conn[0], br = conn[1];
				int n = nd.length;
				for(int j=0; j < n; ++j)
				{
					pw.format("'%s',%f,'%s',%f\n",
						name[i], _bdiag[i], name[nd[j]], _boffdiag[br[j]]);
				}
			}
			else
			{
				pw.format("'%s',%f\n",name[i], _bdiag[i]);
			}
		}
	}
}
