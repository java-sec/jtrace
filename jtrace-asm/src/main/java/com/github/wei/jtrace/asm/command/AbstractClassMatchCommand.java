package com.github.wei.jtrace.asm.command;

import com.github.wei.jtrace.api.command.Argument;
import com.github.wei.jtrace.api.command.ICommand;
import com.github.wei.jtrace.api.transform.matcher.IClassMatcher;
import com.github.wei.jtrace.asm.api.matcher.IMethodMatcher;
import com.github.wei.jtrace.asm.util.MatcherUtil;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractClassMatchCommand implements ICommand{

	@Override
	public Serializable execute(Object... args) throws Exception {
		String className = String.valueOf(args[0]);
		String matchType = String.valueOf(args[2]);
		
		IClassMatcher classMatcher = MatcherUtil.extractClassMatcher(className, matchType);
		
		if(args[1] != null) {
			List<IMethodMatcher> methodMatchers = MatcherUtil.extractMethodMatchers(String.valueOf(args[1]));
			return doMatch(classMatcher, methodMatchers.toArray(new IMethodMatcher[methodMatchers.size()]));
		}
		
		return doMatch(classMatcher);
	}

	protected abstract Serializable doMatch(IClassMatcher classMatcher, IMethodMatcher ...matchers) throws Exception;
	
	
	@Override
	public Argument[] args() {
		return new Argument[]{new Argument("class", "类名，需要全路径", true, String.class),
				new Argument("method", "方法描述，支持1.方法名适配：method, 2.指定参数个数的方法：method(2), 3.精确适配：method(Ljava/lang/String)V 。多个方法用逗号隔开", false, String.class),
				new Argument("matchType", "适配类型", String.class, "extract")};

	}

}
