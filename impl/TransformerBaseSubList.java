package com.powerdata.openpa.impl;

public abstract class TransformerBaseSubList<T extends TransformerBase> extends ACBranchSubList<T> implements TransformerBaseList<T>
{
	TransformerBaseList<T> _src;
	
	public TransformerBaseSubList(TransformerBaseList<T> src, int[] ndx)
	{
		super(src, ndx);
		_src = src;
	}


	@Override
	public float getGmag(int ndx)
	{
		return _src.getGmag(_ndx[ndx]);
	}

	@Override
	public void setGmag(int ndx, float g)
	{
		_src.setGmag(_ndx[ndx], g);
	}

	@Override
	public float[] getGmag()
	{
		return mapFloat(_src.getGmag());
	}

	@Override
	public void setGmag(float[] g)
	{
		for(int i=0; i < _size; ++i)
			_src.setGmag(_ndx[i], g[i]);
	}

	@Override
	public float getBmag(int ndx)
	{
		return _src.getBmag(_ndx[ndx]);
	}

	@Override
	public void setBmag(int ndx, float b)
	{
		_src.setBmag(_ndx[ndx], b);
	}

	@Override
	public float[] getBmag()
	{
		return mapFloat(_src.getBmag());
	}

	@Override
	public void setBmag(float[] b)
	{
		for(int i=0; i < _size; ++i)
			_src.setBmag(_ndx[i], b[i]);
	}

	@Override
	public float getShift(int ndx)
	{
		return _src.getShift(_ndx[ndx]);
	}

	@Override
	public void setShift(int ndx, float sdeg)
	{
		_src.setShift(_ndx[ndx], sdeg);
	}

	@Override
	public float[] getShift()
	{
		return mapFloat(_src.getShift());
	}

	@Override
	public void setShift(float[] sdeg)
	{
		for(int i=0; i < _size; ++i)
			_src.setShift(_ndx[i], sdeg[i]);
	}

	@Override
	public float getFromTap(int ndx)
	{
		return _src.getFromTap(_ndx[ndx]);
	}

	@Override
	public void setFromTap(int ndx, float a)
	{
		_src.setFromTap(_ndx[ndx], a);
	}

	@Override
	public float[] getFromTap()
	{
		return mapFloat(_src.getFromTap());
	}

	@Override
	public void setFromTap(float[] a)
	{
		for(int i=0; i < _size; ++i)
			_src.setFromTap(_ndx[i], a[i]);
	}

	@Override
	public float getToTap(int ndx)
	{
		return _src.getToTap(_ndx[ndx]);
	}

	@Override
	public void setToTap(int ndx, float a)
	{
		_src.setToTap(_ndx[ndx], a);
	}

	@Override
	public float[] getToTap()
	{
		return mapFloat(_src.getToTap());
	}

	@Override
	public void setToTap(float[] a)
	{
		for(int i=0; i < _size; ++i)
			_src.setToTap(_ndx[i], a[i]);
	}
}