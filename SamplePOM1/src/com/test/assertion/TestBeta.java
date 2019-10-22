package com.test.assertion;

public class TestBeta {

	int var1 = 4;
	boolean var2 = false;
	boolean var3 = true;
	private SoftAssert _assert = null;
	
	public void setSoftAssert(SoftAssert _assert){
		this._assert = _assert;
	}
	
	public SoftAssert getSoftAssert(){
		return this._assert;
	}
	
	public void alpha_meth1(){
		try{
			System.out.println("in method::"+new Object(){}.getClass().getEnclosingMethod().getName().toString());
			//Assert.assertEquals(var1, 2+2);
			_assert.assertEquals(var1, 2+2, "var is not equals t0 4");
		}catch(AssertionError e){//code will not come to catch
			_assert.fail("--------expected var1 is not equal to 4");
		}
	}
	
	public void alpha_meth2(){
		try{
			System.out.println("in method::"+new Object(){}.getClass().getEnclosingMethod().getName().toString());
			//Assert.assertTrue(var2);
			_assert.assertTrue(var2, "var2 value is false");
		}catch(AssertionError e){
			_assert.fail("---------var2 value is false");
		}
	}
	
	public void alpha_meth3(){
		try{
			System.out.println("in method::"+new Object(){}.getClass().getEnclosingMethod().getName().toString());
			//Assert.assertNotEquals(var1, 2+2);
			_assert.assertEquals(var1, 7, "var1 is not equal to 7");
		}catch(AssertionError e){
			_assert.fail("---------var1 value is not equal to 7");
		}
	}
	
	public void alpha_meth4(){
		try{
			System.out.println("in method::"+new Object(){}.getClass().getEnclosingMethod().getName().toString());
			//Assert.assertNotNull(var3);
			_assert.assertNotNull(var3, "var3 is null");
		}catch(AssertionError e){
			_assert.fail("---------var3 is null");
		}
	}
}