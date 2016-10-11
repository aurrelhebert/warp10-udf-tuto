# Create your own User Define Function (UDF)

An UDF corresponds to a custom JAVA class defined by a user. An UDF can easily be added to a Warp 10 platform.

In this page, we will learn how to implement an UDF and how to deploy a new UDF in your own platform.

## UDF Interface

The JAVA function have to implement our UDF interface in the WarpScriptRawJavaFunction. 
This class extends  [WarpScriptJavaFunction](https://github.com/cityzendata/warp10-platform/blob/master/warp10/src/main/java/io/warp10/warp/sdk/WarpScriptJavaFunction.java), and requires the implementation of the function "apply".

## Getting starterd

First of all, create a new Gradle project in your favorite IDE, and add a new package in src/main/java: io.warp10.tutorial.

Then create the class HELLOWARP10 that implements WarpScriptRawJavaFunction. 

Inside this build.gradle file notice that it doesn't include in the JAR the WarpScript libraries, but indicated that those library are needed at execution. This is donne when declaring in the build file the following instruction: 
```
provided 'io.warp10:warp10:1.0.1-165-g9c49c1f'
```

This operation is important because it means the UDF doesn't need to be recompiled for each new Warp10 version as it use the current version deployed on the Warp10 cluster.

This udf that we will create takes 2 strings parameters on stack and send a result with a merge of both.

The full code is available on github [here](https://github.com/aurrelhebert/warp10-udf-tuto).

## The Apply function

In this function, first we load the current stack from the parameter

```
WarpScriptStack stack =(WarpScriptStack) arg0.get(0);
```

Then we store both parameters that were on top of it

```
Object firstText = stack.pop();
Object secondText = stack.pop();
```

We check if both parameters are valid (if they are String it enougth in this exemple)
```
if (!(firstText instanceof String)) {
  throw new WarpScriptJavaFunctionException("First param must be a String");
  //throw new WarpScriptJavaFunctionException();
}
```

Finally we push back a new element on the stack which is the concatenation of both string.
```
stack.push(firstText.toString() + ' ' + secondText.toString());
```
The object returned by the function is arg0 (which contain the stack updated).

## Deploy it

First compile this project
```
gradle shadowJar
```

Open the Warp 10 conf file and you have to configure both following parameters to allow udf. Then copy the jar generated by gradle in warpscript.jars.directory.

```
warpscript.jars.directory = /some/dir/udf
warpscript.jars.refresh = 60000
```

Then just move the generated jar in this folder.

## Use your UDF

To execute it just run the following WarpScript on your Warp10 backend.

```
    'WARP 10' 'HELLO'
    'io.warp10.tutorial.HELLOWARP10' UDF
```

Congrats you managed to create your first UDF for a warp10 cluster !


