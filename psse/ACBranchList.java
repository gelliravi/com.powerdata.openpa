package com.powerdata.openpa.psse;

import com.powerdata.openpa.tools.AbstractBaseObject;
import com.powerdata.openpa.tools.BaseList;
import com.powerdata.openpa.tools.Complex;

public class ACBranchList extends BaseList<ACBranch>
{
	public static final ACBranchList Empty = new ACBranchList() 
	{
		@Override
		public String getObjectID(int ndx) throws PsseModelException {return null;}
		@Override
		public int size() {return 0;}
	};

	class ACBranchObj extends AbstractBaseObject implements ACBranch
	{
		public ACBranchObj(int ndx) {super(ACBranchList.this, ndx);}
		@Override
		public Bus getFromBus() throws PsseModelException {return ACBranchList.this.getFromBus(_ndx);}
		@Override
		public Bus getToBus() throws PsseModelException {return ACBranchList.this.getToBus(_ndx);}
		@Override
		public Complex getZ() throws PsseModelException {return ACBranchList.this.getZ(_ndx);}
		@Override
		public Complex getFromY() throws PsseModelException {return ACBranchList.this.getFromY(_ndx);}
		@Override
		public Complex getToY() throws PsseModelException {return ACBranchList.this.getToY(_ndx);}
		@Override
		public float getFromTap() throws PsseModelException {return ACBranchList.this.getFromTap(_ndx);}
		@Override
		public float getToTap() throws PsseModelException {return ACBranchList.this.getToTap(_ndx);}
		@Override
		public float getPhaseShift() throws PsseModelException {return ACBranchList.this.getPhaseShift(_ndx);}
	}
	
	int _nlines;
	int _ntransformers;
	int _size;
	LineList _lines;
	TransformerList _transformers;
	PhaseShifterList _phaseshifters;
	
	ACBranchList() {super();}

	public ACBranchList(LineList l, TransformerList xf, PhaseShifterList ps)
			throws PsseModelException
	{
		_lines = l;
		_transformers = xf;
		_phaseshifters = ps;
		_nlines = _lines.size();
		_ntransformers = _transformers.size();
		_size = _nlines + _ntransformers + ps.size();
	}

	/* Standard object retrieval */
	/** Get an AreaInterchange by it's index. */
	@Override
	public ACBranch get(int ndx) { return new ACBranchObj(ndx); }
	/** Get an AreaInterchange by it's ID. */
	@Override
	public ACBranch get(String id) { return super.get(id); }

	public Complex getZ(int ndx) throws PsseModelException {return findBranch(ndx).getZ();}
	public Bus getToBus(int ndx) throws PsseModelException {return findBranch(ndx).getToBus();}
	public Bus getFromBus(int ndx) throws PsseModelException {return findBranch(ndx).getFromBus();}
	public float getPhaseShift(int ndx) throws PsseModelException {return findBranch(ndx).getPhaseShift();}
	public float getToTap(int ndx) throws PsseModelException {return findBranch(ndx).getToTap();}
	public float getFromTap(int ndx) throws PsseModelException {return findBranch(ndx).getFromTap();}
	public Complex getToY(int ndx) throws PsseModelException {return findBranch(ndx).getToY();}
	public Complex getFromY(int ndx) throws PsseModelException {return findBranch(ndx).getFromY();}

	ACBranch findBranch(int ndx)
	{
		if (ndx < _nlines)
		{
			return _lines.get(ndx);
		}
		else if ((ndx-=_nlines) < _ntransformers)
		{
			return _transformers.get(ndx);
		}
		else return _transformers.get(ndx-_ntransformers);
	}

	@Override
	public String getObjectID(int ndx) throws PsseModelException
	{
		return findBranch(ndx).getObjectID();
	}

	@Override
	public String getObjectName(int ndx) throws PsseModelException
	{
		return findBranch(ndx).getObjectName();
	}

	@Override
	public int size() {return _size;}
}

