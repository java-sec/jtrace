package com.github.wei.jtrace.core.transform.matchers;

import java.util.Collections;
import java.util.List;

import com.github.wei.jtrace.api.clazz.IClassDescriberTree;
import com.github.wei.jtrace.api.exception.ClassMatchException;
import com.github.wei.jtrace.api.transform.matcher.IClassMatcher;
import com.github.wei.jtrace.api.transform.matcher.IMatchedListener;
import com.github.wei.jtrace.api.transform.matcher.IMethodMatcherWithContext;
import com.github.wei.jtrace.api.transform.matcher.MatcherContext;

public class Matcher implements IClassMatcher{
	private long id;
	private IClassMatcher classMatcher;
	private List<IMethodMatcherWithContext> methodMatchers = Collections.emptyList();
	private IMatchedListener matchedListener;
	private MatcherContext context = new MatcherContext();
	
	public Matcher(long id, MatcherContext context, IClassMatcher classMatcher, List<IMethodMatcherWithContext> methodMatchers) {
		this(id, context, classMatcher, methodMatchers, null);
	}
	
	public Matcher(long id, MatcherContext context, IClassMatcher classMatcher, List<IMethodMatcherWithContext> methodMatchers, IMatchedListener matchedListener) {
		this.id = id;
		this.classMatcher = classMatcher;
		if(methodMatchers != null) {
			this.methodMatchers = methodMatchers;
		}
		
		this.matchedListener = matchedListener;
		this.context.merge(context);
	}

	public long getId() {
		return id;
	}
	
	public MatcherContext getContext() {
		return context;
	}
	
	public IMatchedListener getMatchedListener() {
		return matchedListener;
	}
	
	public List<IMethodMatcherWithContext> getMethodMatchers() {
		return methodMatchers;
	}

	@Override
	public boolean matchClass(IClassDescriberTree descr) throws ClassMatchException {
		return classMatcher.matchClass(descr);
	}
	
}