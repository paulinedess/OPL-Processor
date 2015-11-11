package com.iagl.processor;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractManualProcessor;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.visitor.filter.TypeFilter;
import util.SaveMap;

public class ConstructorParametersMapProcessor extends AbstractManualProcessor{

	@Override
	public void process() {			
	
      // On recupere la liste des contructeurs
      List<CtConstructor> constructors = getFactory().Package().getRootPackage().getElements(new TypeFilter(CtConstructor.class));

      for (CtConstructor ctor : constructors) {
          generateMapConstructorParameters(ctor);		
      }
	}
	
	/**
	 * Generate the maping of all constructor parameters
	 * @param constructor
	 */
	private void generateMapConstructorParameters(CtConstructor<? extends Object> constructor){
		
		List<String> parameters = new ArrayList<String>();
		
		for(CtParameter<?> parameter : constructor.getParameters()){
			parameters.add(parameter.getType().toString());
		}	
		SaveMap.saveConstructorParameters(constructor.getType().toString(), parameters);		
	}
}