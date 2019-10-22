package com.test.assertion;

import com.test.assertion.TestBeta;

import org.testng.annotations.Test;

public class TestAlpha {
	
	TestBeta test_beta = null;
	SoftAssert _tassert;
	
	public void beta_test1(){
		//test_beta = new TestBeta();
		System.out.println("******************************************************");
		System.out.println("\t\tin method::"+new Object(){}.getClass().getEnclosingMethod().getName().toString());
		test_beta.alpha_meth1();
		test_beta.alpha_meth2();
		test_beta.alpha_meth3();
		test_beta.alpha_meth4();
		System.out.println("******************************************************");
		//test_beta = null;
	}
	
	public void beta_test2(){
		//test_beta = new TestBeta();
		System.out.println("******************************************************");
		System.out.println("\t\tin method::"+new Object(){}.getClass().getEnclosingMethod().getName().toString());
		test_beta.alpha_meth2();
		test_beta.alpha_meth4();
		test_beta.alpha_meth3();
		test_beta.alpha_meth1();
		System.out.println("******************************************************");
		//test_beta = null;
	}
	
	@Test
	public void test_meth1(){
		//initiate the SoftAssert for executing test cases
		_tassert = new SoftAssert();
		test_beta = new TestBeta();
		test_beta.setSoftAssert(_tassert);
		beta_test1();
		beta_test2();
		//validating all the assertions of the test at the end of the test cases
		_tassert = test_beta.getSoftAssert();
		try{
			_tassert.assertAll();
		}catch(AssertionError e){
			e.printStackTrace();
		}
	}

}