package com.github.wei.jtrace.core.advisor;

import java.util.ArrayList;
import java.util.List;

import com.github.wei.jtrace.agent.IAdvice;
import com.github.wei.jtrace.api.advice.IAdviceListener;

public class AdviceInvoker implements IAdvice{
	private List<IAdviceListener> listeners = new ArrayList<IAdviceListener>();
	
	public AdviceInvoker(List<IAdviceListener> listeners) {
		this.listeners.addAll(listeners);
	}
	
	@Override
	public Object[] onBegin(Object[] args) {
		for(IAdviceListener listener : listeners) {
			Object[] temp = listener.onBegin(args);
			if(temp != null && temp.length == args.length) {
				args = temp;
			}
		}
		return args;
	}

	@Override
	public Object onReturn(Object obj) {
		for(IAdviceListener listener : listeners) {
			obj = listener.onReturn(obj);
		}
		return obj;
	}

	@Override
	public void onThrow(Throwable thr) {
		for(IAdviceListener listener : listeners) {
			listener.onThrow(thr);
		}
	}

	@Override
	public void onInvoke(Integer lineNumber, String own, String name, String desc, boolean itf) {
		for(IAdviceListener listener : listeners) {
			listener.onInvoke(lineNumber, own, name, desc, itf);;
		}
	}

}
