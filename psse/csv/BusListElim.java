package com.powerdata.openpa.psse.csv;

import java.util.HashMap;

import com.powerdata.openpa.tools.ComplexList;
import com.powerdata.openpa.tools.LinkNet;

public class BusListElim extends BusList
{

	public BusListElim() {super();}
	public BusListElim(BusListRaw rbuses, LinkNet lnet, PsseModel model)
	{
		super(model);
		int[][] groups = lnet.findGroups();

		HashMap<Integer,Integer> elim = new HashMap<>();
		int nelim = 0;
		for(int[] grp : groups)
		{
			int ngrp = grp.length;
			int targ = grp[0];
			for(int i=1; i < ngrp; ++i)
			{
				elim.put(grp[i], targ);
				++nelim;
			}
		}
		System.out.format("Eliminating %d buses\n", nelim);
		int nrbus = rbuses.size();
		
		_size = nrbus - nelim;
		_i = new int[_size];
		_name = new String[_size];
		_ide = new int[_size];
		_area = new int[_size];
		_zone = new int[_size];
		_owner = new int[_size];
		_vm = new float[_size];
		_va = new float[_size];
		_gl = new float[_size];
		_bl = new float[_size];
		_ids = new String[_size];
		_basekv = new float[_size];
		_mm = new ComplexList(_size, true);
		
		HashMap<String,Integer> id2ndx = new HashMap<>(nrbus);
		int nbus = 0;
		for(int i=0; i < nrbus; ++i)
		{
			Integer elimto = elim.get(i);
			String objid = rbuses.getObjectID(i);
			if (objid.equals("22938"))
			{
				int xxx = 5;
			}
			if (elimto == null)
			{
				_i[nbus] = rbuses.getI(i);
				_name[nbus] = rbuses.getNAME(i);
				_ids[nbus] = rbuses.getObjectID(i);
				_ide[nbus] = rbuses.getIDE(i);
				_area[nbus] = rbuses.getAREA(i);
				_zone[nbus] = rbuses.getZONE(i);
				_owner[nbus] = rbuses.getOWNER(i);
				_vm[nbus] = rbuses.getVM(i);
				_va[nbus] = rbuses.getVA(i);
				_gl[nbus] = rbuses.getGL(i);
				_bl[nbus] = rbuses.getBL(i);
				id2ndx.put(objid, nbus);
				++nbus;
			}
			else
			{
				id2ndx.put(objid, elimto);
			}
		}
		_idToNdx = id2ndx;
	}


}
