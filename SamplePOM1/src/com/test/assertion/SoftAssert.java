package com.test.assertion;

import java.util.Map;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

public class SoftAssert extends Assertion{
	
	private Map<AssertionError, IAssert> m_errors = Maps.newHashMap();
	
	//Constructor: SoftAssert
	public SoftAssert(){
		System.out.println("calling customized SoftAssert constructor");
	}
	
	//Method: doAssert(IAssert)
	//Description: Used to perform assertion and invoke executeAssert(IAssert) method for each assertion in test script 
	@Override
	protected void doAssert(IAssert assertCommand){
		onBeforeAssert(assertCommand);
		try{
			executeAssert(assertCommand);
		}catch(AssertionError aeEX){
			m_errors.put(aeEX, assertCommand);
		}finally{
			onAfterAssert(assertCommand);
		}
	}
	
	//Method: executeAssert(IAssert)
	//Description: Execute each assert and for each failure put the Assert error in Map<AssertionError, IAssert> object "m_errors"
	@Override
	public void executeAssert(IAssert a){
		try{
			a.doAssert();
			onAssertSuccess(a);
		}catch(AssertionError ex){
			m_errors.put(ex, a);
			onAssertFailure(a, ex);
		}
	}
	
	/*
	 *Method: onAssertFailure(IAssert, AssertionError)
	 * Description: It captures activity like screenshot capture once any assert get fail and log the screenshot and error message to the extent test object.
	 */
	@Override
	public void onAssertFailure(IAssert a, AssertionError ex){
		try{
			System.out.println("getting screenshot on Assert Failure");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.testng.asserts.Assertion#onAssertSuccess(org.testng.asserts.IAssert)
	 * Method: onAssertSuccess(IAssert)
	 * Description: It captures activity like screenshot
	 */
	@Override
	public void onAssertSuccess(IAssert a){
		try{
			System.out.println("getting screenshot on Assert Success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Method: assertAll()
	 * Description: Evaluate the all assertion errors collected in Map<AssertionError, IAssert> at the end of the Test execution
	 */
	public void assertAll(){
		if(!m_errors.isEmpty())
		{
			StringBuilder sb = new StringBuilder("[FUNCTIONAL FAILURE - ASSERTION ERROR ------------ ");
			boolean first = true;
			for(Map.Entry<AssertionError, IAssert> ae : m_errors.entrySet())
			{
				if(first)
				{
					first = false;
				}
				else
				{
					sb.append(", ");
				}
				sb.append(ae.getValue().getMessage());
			}
			throw new AssertionError(sb.toString());
		}
	}
}