package com.powerdata.openpa.impl;

import com.powerdata.openpa.ColumnMeta;
import com.powerdata.openpa.ListMetaType;
import com.powerdata.openpa.Transformer;
import com.powerdata.openpa.TransformerList;

public class TransformerListI extends TransformerBaseListI<Transformer> implements TransformerList
{
	static final TransBaseEnum _PFld = new TransBaseEnum()
	{
		@Override
		public ColumnMeta fbus() {return ColumnMeta.TfmrBUSFROM;}
		@Override
		public ColumnMeta tbus() {return ColumnMeta.TfmrBUSTO;}
		@Override
		public ColumnMeta insvc() {return ColumnMeta.TfmrINSVC;}
		@Override
		public ColumnMeta fp() {return ColumnMeta.TfmrPFROM;}
		@Override
		public ColumnMeta fq() {return ColumnMeta.TfmrQFROM;}
		@Override
		public ColumnMeta tp() {return ColumnMeta.TfmrPTO;}
		@Override
		public ColumnMeta tq() {return ColumnMeta.TfmrQTO;}
		@Override
		public ColumnMeta id() {return ColumnMeta.TfmrID;}
		@Override
		public ColumnMeta name() {return ColumnMeta.TfmrNAME;}
		@Override
		public ColumnMeta r() {return ColumnMeta.TfmrR;}
		@Override
		public ColumnMeta x() {return ColumnMeta.TfmrX;}
		@Override
		public ColumnMeta gmag() {return ColumnMeta.TfmrGMAG;}
		@Override
		public ColumnMeta bmag() {return ColumnMeta.TfmrBMAG;}
		@Override
		public ColumnMeta shift() {return ColumnMeta.TfmrANG;}
		@Override
		public ColumnMeta ftap() {return ColumnMeta.TfmrTAPFROM;}
		@Override
		public ColumnMeta ttap() {return ColumnMeta.TfmrTAPTO;}
	};

	public TransformerListI() {super();}
	
	public TransformerListI(PAModelI model, int[] keys)
	{
		super(model, keys, _PFld);
	}
	public TransformerListI(PAModelI model, int size)
	{
		super(model, size, _PFld);
	}

	@Override
	public Transformer get(int index)
	{
		return new Transformer(this, index);
	}

	@Override
	protected ListMetaType getMetaType()
	{
		return ListMetaType.Transformer;
	}

}