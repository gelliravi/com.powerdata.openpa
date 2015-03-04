package com.powerdata.openpa;

import java.util.Arrays;
import java.util.WeakHashMap;
import com.powerdata.openpa.impl.AreaSubList;
import com.powerdata.openpa.impl.BusSubList;
import com.powerdata.openpa.impl.GenSubList;
import com.powerdata.openpa.impl.IslandSubList;
import com.powerdata.openpa.impl.LineSubList;
import com.powerdata.openpa.impl.LoadSubList;
import com.powerdata.openpa.impl.OwnerSubList;
import com.powerdata.openpa.impl.PhaseShifterSubList;
import com.powerdata.openpa.impl.SVCSubList;
import com.powerdata.openpa.impl.SeriesCapSubList;
import com.powerdata.openpa.impl.SeriesReacSubList;
import com.powerdata.openpa.impl.ShuntCapSubList;
import com.powerdata.openpa.impl.ShuntReacSubList;
import com.powerdata.openpa.impl.StationSubList;
import com.powerdata.openpa.impl.SwitchSubList;
import com.powerdata.openpa.impl.SwitchedShuntSubList;
import com.powerdata.openpa.impl.TransformerSubList;
import com.powerdata.openpa.impl.TwoTermDCLineSubList;
import com.powerdata.openpa.impl.VoltageLevelSubList;

public class SubLists
{
	public static AreaList getAreaSublist(AreaList list, int[] indexes)
	{
		return new AreaSubList(list, indexes);
	}
	
	public static BusList getBusSublist(BusList list, int[] indexes)
	{
		return new BusSubList(list, indexes);
	}
	
	public static IslandList getIslandSublist(IslandList list, int[] indexes)
	{
		return new IslandSubList(list, indexes);
	}
	
	public static OwnerList getOwnerSublist(OwnerList list, int[] indexes)
	{
		return new OwnerSubList(list, indexes);
	}
	
	public static StationList getStationSublist(StationList list, int[] indexes)
	{
		return new StationSubList(list, indexes);
	}

	public static VoltageLevelList getVoltageLevelSublist(VoltageLevelList list, int[] indexes)
	{
		return new VoltageLevelSubList(list, indexes);
	}
	
	public static ShuntCapList getShuntCapSublist(ShuntCapList list, int[] indexes)
	{
		return new ShuntCapSubList(list, indexes);
	}
	
	public static ShuntCapList getShuntCapInsvc(ShuntCapList list) throws PAModelException
	{
		return getShuntCapSublist(list, getInServiceIndexes(list));
	}
	
	public static ShuntReacList getShuntReacSublist(ShuntReacList list, int[] indexes)
	{
		return new ShuntReacSubList(list, indexes);
	}
	
	public static ShuntReacList getShuntReacInsvc(ShuntReacList list) throws PAModelException
	{
		return getShuntReacSublist(list, getInServiceIndexes(list));
	}
	
	public static GenList getGenSublist(GenList list, int[] indexes)
	{
		return new GenSubList(list, indexes);
	}
	
	public static GenList getGenInsvc(GenList list) throws PAModelException
	{
		return getGenSublist(list, getInServiceIndexes(list));
	}
	
	public static LoadList getLoadSublist(LoadList list, int[] indexes)
	{
		return new LoadSubList(list, indexes);
	}
	
	public static LoadList getLoadInsvc(LoadList list) throws PAModelException
	{
		return getLoadSublist(list, getInServiceIndexes(list));
	}
	
	public static SVCList getSVCSublist(SVCList list, int[] indexes)
	{
		return new SVCSubList(list, indexes);
	}
	
	public static SVCList getSVCInsvc(SVCList list) throws PAModelException
	{
		return getSVCSublist(list, getInServiceIndexes(list));
	}
	
	public static SwitchedShuntList getSwitchedShuntSublist(SwitchedShuntList list, int[] indexes)
	{
		return new SwitchedShuntSubList(list, indexes);
	}
	
	public static SwitchedShuntList getSwitchedShuntInsvc(SwitchedShuntList list) throws PAModelException
	{
		return getSwitchedShuntSublist(list, getInServiceIndexes(list));
	}
	
	public static LineList getLineSublist(LineList list, int[] indexes)
	{
		return new LineSubList(list, indexes);
	}
	
	public static LineList getLineInsvc(LineList list) throws PAModelException
	{
		return getLineSublist(list, getInServiceIndexes(list));
	}
	
	public static PhaseShifterList getPhaseShifterSublist(PhaseShifterList list, int[] indexes)
	{
		return new PhaseShifterSubList(list, indexes);
	}
	
	public static PhaseShifterList getPhaseShifterInsvc(PhaseShifterList list) throws PAModelException
	{
		return getPhaseShifterSublist(list, getInServiceIndexes(list));
	}
	
	public static SeriesCapList getSeriesCapSublist(SeriesCapList list, int[] indexes)
	{
		return new SeriesCapSubList(list, indexes);
	}
	
	public static SeriesCapList getSeriesCapInsvc(SeriesCapList list) throws PAModelException
	{
		return getSeriesCapSublist(list, getInServiceIndexes(list));
	}
	
	public static SeriesReacList getSeriesReacSublist(SeriesReacList list, int[] indexes)
	{
		return new SeriesReacSubList(list, indexes);
	}
	
	public static SeriesReacList getSeriesReacInsvc(SeriesReacList list) throws PAModelException
	{
		return getSeriesReacSublist(list, getInServiceIndexes(list));
	}
	
	public static TransformerList getTransformerSublist(TransformerList list, int[] indexes)
	{
		return new TransformerSubList(list, indexes);
	}
	
	public static TransformerList getTransformerInsvc(TransformerList list) throws PAModelException
	{
		return getTransformerSublist(list, getInServiceIndexes(list));
	}
	
	public static SwitchList getSwitchSublist(SwitchList list, int[] indexes)
	{
		return new SwitchSubList(list, indexes);
	}

	public static TwoTermDCLineList getTwoTermDCLineSublist(TwoTermDCLineList list, int[] indexes)
	{
		return new TwoTermDCLineSubList(list, indexes);
	}
	
	public static TwoTermDCLineList getTwoTermDCLineInsvc(TwoTermDCLineList list) throws PAModelException
	{
		return getTwoTermDCLineSublist(list, getInServiceIndexes(list));
	}
	
	public static int[] getInServiceIndexes(OutOfServiceList<? extends OutOfService> list) throws PAModelException
	{
		int n = list.size();
		int[] rv = new int[n];
		int size = 0;
		boolean[] oos = list.isOutOfSvc();
		for (int i = 0; i < n; ++i)
			if (!oos[i]) rv[size++] = i;
		return Arrays.copyOf(rv, size);
		
	}

}
