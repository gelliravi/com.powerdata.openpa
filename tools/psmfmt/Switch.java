package com.powerdata.openpa.tools.psmfmt;

public enum Switch implements VersionedDoc
{
	ID, Name, Node1, Node2, SwitchType;

	@Override
	public String getVersion() {return "1.10";}
}
