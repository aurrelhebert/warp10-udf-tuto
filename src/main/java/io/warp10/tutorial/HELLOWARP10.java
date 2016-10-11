package io.warp10.tutorial;

import java.util.List;

import io.warp10.script.WarpScriptException;
import io.warp10.script.WarpScriptStack;
import io.warp10.warp.sdk.WarpScriptJavaFunctionException;
import io.warp10.warp.sdk.WarpScriptRawJavaFunction;

public class HELLOWARP10 implements WarpScriptRawJavaFunction {
  
  public List<Object> apply(List<Object> arg0) throws WarpScriptJavaFunctionException {
    
    //
    // Get current stack
    //
    
    WarpScriptStack stack =(WarpScriptStack) arg0.get(0);
    
    //
    // Get params
    //
    
    Object firstText = stack.pop();
    Object secondText = stack.pop();
    
    if (!(firstText instanceof String)) {
      throw new WarpScriptJavaFunctionException("First param must be a String");
      //throw new WarpScriptJavaFunctionException();
    }
    if (!(secondText instanceof String)) {
      throw new WarpScriptJavaFunctionException("Second param must be a String");
      //throw new WarpScriptJavaFunctionException();
    }
    try {
      stack.push(firstText.toString() + ' ' + secondText.toString());
    } catch (WarpScriptException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new WarpScriptJavaFunctionException("Stack exception" + e.getMessage() + e.getStackTrace().toString());
      //throw new WarpScriptJavaFunctionException();
    }
    
    return arg0;  
  }
  
  //
  // Not necessary here
  //
  public int argDepth() {
    return 0;
  }

  //
  // Not necessary here
  //
  public boolean isProtected() {
    // TODO Auto-generated method stub
    return false;
  }

}
