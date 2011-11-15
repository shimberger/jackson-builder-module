package de.himberger.jackson.builder;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Test;

import de.himberger.jackson.builder.dummies.DummyClass;
import de.himberger.jackson.builder.dummies.DummyClassWithArguments;

public abstract class FindMethodAbstractTestCase {

	public abstract MethodFinder getNonExistingBuilderMethodFinder();
	
	public abstract MethodFinder getExistingBuilderMethodFinder();

	public abstract MethodFinder getNonExistingInstanceMethodFinder();
	
	public abstract MethodFinder getExistingInstanceMethodFinder();
	
	@Test
	public void findByAnnotationStatic() {
		MethodFinder finder = getExistingBuilderMethodFinder();
		Method method = finder.findInStatic(DummyClass.class);
		assertNotNull(method);
	}

	@Test
	public void findByAnnotation() {
		MethodFinder finder = getExistingInstanceMethodFinder();
		Method method = finder.findIn(DummyClass.class);
		assertNotNull(method);
	}
	
	@Test
	public void nonExistingAnnotationReturnsNullStatic() {
		MethodFinder finder = getNonExistingBuilderMethodFinder();
		Method method = finder.findInStatic(DummyClass.class);
		assertNull(method);
	}	
	
	@Test
	public void nonExistingAnnotationReturnsNull() {
		MethodFinder finder = getNonExistingInstanceMethodFinder();
		Method method = finder.findInStatic(DummyClass.class);
		assertNull(method);
	}	
	
	@Test(expected=IllegalStateException.class)
	public void methodArgumentsThrowException() {
		MethodFinder finder = getExistingBuilderMethodFinder();
		Method method = finder.findInStatic(DummyClassWithArguments.class);
		assertTrue(false);
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void nullArgsForFindInThrowsException() {
		MethodFinder finder = getExistingBuilderMethodFinder();
		finder.findIn(null);
		assertTrue(false);
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void nullArgsForFindInStaticThrowsException() {
		MethodFinder finder = getExistingBuilderMethodFinder();
		finder.findInStatic(null);
		assertTrue(false);
	}	
	
}
