package com.raeffray.csv.loader;

import java.util.ArrayList;
import java.util.Collection;

import com.raeffray.csv.InstanceHandler;
import com.raeffray.graph.RelationshipDescriber;
import com.raeffray.raw.data.RawData;

public class ConnectedNodesLoader implements InstanceHandler<RawData> {

	private Collection<RelationshipDescriber> relationshipDescribers;
	
	private Collection<RawData> rawDataList;
	

	public ConnectedNodesLoader(
			Collection<RelationshipDescriber> relationshipDescribers) {
		super();
		this.relationshipDescribers = relationshipDescribers;
		rawDataList = new ArrayList<RawData>();
	}

	@Override
	public void processInstance(RawData instance) {
		if(instance!=null){
			rawDataList.add(instance);
		}
	}

	@Override
	public void endProcess() {
		System.out.println("rawDataList: ["+rawDataList.size()+"]");
	}

}
