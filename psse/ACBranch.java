package com.powerdata.openpa.psse;

import com.powerdata.openpa.tools.Complex;

public interface ACBranch extends TwoTermDev
{
	/** Get complex impedance p.u. on 100 MVA and bus base kv */
	public Complex getZ() throws PsseModelException;
	/** get from-side Admittance (charging or magnetizing) p.u. on 100MVA base and bus base KV*/
	public Complex getFromY() throws PsseModelException;
	/** get to-side Admittance (charging or magnetizing) p.u. on 100MVA base and bus base KV*/
	public Complex getToY() throws PsseModelException;
	/** get from-side off-nominal tap ratio p.u. on 100MVA base and bus base KV */
	public float getFromTap() throws PsseModelException;
	/** get to-side off-nominal tap ratio p.u on 100MVA base and bus base KV */
	public float getToTap() throws PsseModelException;
	/** get phase shift through branch (in RAD)*/
	public float getPhaseShift() throws PsseModelException;
}
