package com.github.wei.jtrace.core.transform.matchers;

import com.github.wei.jtrace.api.clazz.MethodDescriber;
import com.github.wei.jtrace.api.transform.matcher.IMethodMatcher;

public class MethodOrMatcher implements IMethodMatcher {
	
	private IMethodMatcher[] matchers = null;
	public MethodOrMatcher(IMethodMatcher ...matchers) {
		this.matchers = matchers;
	}
	
	@Override
	public boolean match(MethodDescriber target) {
		for(IMethodMatcher matcher: matchers) {
			if(matcher != null && matcher.match(target)) {
				return true;
			}
		}
		return false;
	}

}
