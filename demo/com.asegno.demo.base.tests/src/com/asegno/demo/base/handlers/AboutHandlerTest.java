package com.asegno.demo.base.handlers;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.asegno.demo.base.handlers.AboutHandler;

public class AboutHandlerTest {

	@Test
	public void testGetMessage() {
		assertThat(new AboutHandler().getMessage(), CoreMatchers.containsString("Tycho Demo"));
	}

}
