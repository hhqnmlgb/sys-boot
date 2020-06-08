package com.sunys.core.run.shell;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.sunys.facade.run.StateType;

/**
 * shell状态类型
 * ShellStateType
 * @author sunys
 * @date May 17, 2020
 */
public class ShellStateType implements StateType {

	public static final String BIN_BASH_NAME = "BIN_BASH";
	
	public static final String INPUT_USERNAME_NAME = "INPUT_USERNAME";
	
	public static final String INPUT_PASSWORD_NAME = "INPUT_PASSWORD";
	
	public static final String CONFIRM_KEY_NAME = "CONFIRM_KEY";

	//#
	//$
	//>
	public static final Pattern BIN_BASH_PATTERN = Pattern.compile(" ?[>\\$#] ?$");
	
	//login:
	public static final Pattern INPUT_USERNAME_PATTERN = Pattern.compile("(?i)login: ?$");
	
	//password:
	public static final Pattern INPUT_PASSWORD_PATTERN = Pattern.compile("(?i)password: ?$");
	
	//continue connecting (yes/no)?
	public static final Pattern CONFIRM_KEY_PATTERN = Pattern.compile("(?i)continue connecting ?\\(yes/no\\)\\? ?$");
	
	
	public static final ShellStateType BIN_BASH = new ShellStateType(BIN_BASH_NAME, BIN_BASH_PATTERN);
	
	public static final ShellStateType INPUT_USERNAME = new ShellStateType(INPUT_USERNAME_NAME, INPUT_USERNAME_PATTERN);
	
	public static final ShellStateType INPUT_PASSWORD = new ShellStateType(INPUT_PASSWORD_NAME, INPUT_PASSWORD_PATTERN);
	
	public static final ShellStateType CONFIRM_KEY = new ShellStateType(CONFIRM_KEY_NAME, CONFIRM_KEY_PATTERN);
	
	
	static {
		/*
		Evolution
		BIN_BASH.set.add(CONFIRM_KEY);
		BIN_BASH.set.add(INPUT_USERNAME);
		BIN_BASH.set.add(INPUT_PASSWORD);
		BIN_BASH.set.add(BIN_BASH);
		
		CONFIRM_KEY.set.add(INPUT_PASSWORD);
		CONFIRM_KEY.set.add(BIN_BASH);
		
		INPUT_USERNAME.set.add(INPUT_PASSWORD);
		INPUT_USERNAME.set.add(BIN_BASH);
		
		INPUT_PASSWORD.set.add(INPUT_USERNAME);
		INPUT_PASSWORD.set.add(INPUT_PASSWORD);
		INPUT_PASSWORD.set.add(BIN_BASH);
		*/
	}

	private Map<ShellStateType, ShellState> typeStateMap = new HashMap<>();
	
	private String name;
	
	private Pattern pattern;

	public ShellStateType(String name, Pattern pattern) {
		this.name = name;
		this.pattern = pattern;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Set<ShellStateType> nexts() {
		return typeStateMap.keySet();
	}
	
	@Override
	public ShellState getState(StateType shellStateType) {
		return typeStateMap.get(shellStateType);
	}
	
	public void addState(ShellStateType shellStateType, ShellState state) {
		typeStateMap.put(shellStateType, state);
	}
	
	public boolean match(String str) {
		boolean find = pattern.matcher(str).find();
		return find;
	}
}
