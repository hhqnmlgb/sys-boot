package com.sunys.facade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sunys.facade.run.Run;
import com.sunys.facade.run.RunFactory;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Factory {

	Class<? extends RunFactory<? extends Run>> value();
}
