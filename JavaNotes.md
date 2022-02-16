#Java: The Complete Reference, Eleventh Edition, 11th Edition
#####by Herbert Schildt

##Chapter 2:
- New keyword var introduced in jdk10, it infers data type from initialization(called type inference)
- Declaring variable - type identifier [ = value ][, identifier [= value ] …];
- Array syntax
  - {type} [] variable = new {type}[size]
  - Or {type} variable[] = new {type}[size]
  - Or variable  = new {type}[]{e1 , e2 , e3 ….}
  - Or variable = { e1 , e2 , e3 …}
- Array has a property - length

##Chapter 3, 4 & 5:
- Switch allows a string since Java7
- Multiple statements in init and iter section in classical for loops are separated by commas for(init;cond;iter)
- For-each loop syntax is for( type variable: collection). Type can be “var” (jdk10)
- String has a method called length()

##Chapter 7:
- Inner class can be static and non-static. Static inner classes are not very useful. Classes can be in local block scope as well.
- VARARGS - variable length argos supported since java 5
  - Syntax : function(type a , type … b)
    - B will be accessible inside the method as an array

##Chapter 8 & 9:
- Super keyword has 2 distinct uses 
  - As super() - in constructor
  - As super.<methods> - this is similar to this operator, but referring super class.
- Fundamental meaning and purpose of abstract class remain unchanged. You cannot instantiate an abstract class.
- Final has 2 other purposes other than defining a constant. Prevent overriding of a method and prevent subclassing of a class
- Type-inference would only consider the return type of method if a method call is used to initialize a “var”. 
  So if method returns a subclass but declares a super-class as return type. The “var” would be inferred as a superclass.
- Interfaces have changed significantly since Java8. 
- Traditional pre-JDK-8 interfaces
  - It is a contract(what), no implementations(how).
  - Interface can be default or public
  - All methods declared are implicitly public
  - Any fields declared are implicitly public, static and final with initial values- basically constants.
  - Interfaces can extend other interfaces.
- JDK-8 interface enhancements - these are special cases and intent of interfaces are not changed by these enhancements.
  - Default method, this has an implementation. Motivation for this is
    - Avoid breaking existing code when an interface is enhanced with new methods
    - Making certain methods in the interface optional.
    - Default implementation of a method can be explicitly called in class - InterfaceName.super.methodName( )
    - In case of conflict of default method from multiple interfaces, class must re-declare the conflicting method, else compiler will complain.
  - Static method - These methods are placed in Interface as a convenient location for helpers. 
    - They are not inherited by classes or extending interfaces. 
    - They should be called as InterfaceName.staticMethodName
  - Private methods - These are purely helpers to default methods.
    - Can be called only from default methods of the interface
    - Or can be called from another private method from the interface.

##Chapter 10:
- The exception handling concepts in Java remains largely unchanged.
- Hierarchy of Throwable->(Error, (Exception->RuntimeException)) remains.
- try-catch(es)-finally handler along with throws declaration, throw keyword to throw an exception etc.. , all of them remain as-is.
- JDK-7 improvements
  - Try-with resources - described chapter-13
  - Multi-catch - Syntactic sugar to handle multiple exceptions in single catch block - catch(ArithmeticException | ArrayIndexOutOfBoundsException e)
  - More precise rethrow (or final rethrow) - again a syntactic sugar-  it is an enhancement to java behaviour. 
    Compiler checks what specific exceptions can actually be thrown in a block of code and expects you to declare only that,
   rather than just looking at what is caught-thrown in the catch-block, for example - you might want to handle all exceptions with similar fashion.
    So just catch Exception and re-throw, but Java will check what exception can actually be caught and expects you to declare only that.
   (but you should not re-assign the error variable for that in the catch-block).

##Chapter 11:
- The concept of Threads - JVM is a process for the OS. Multi-tasking within a JVM is by threads. OS also supports thread.
- Thread class in Java is basis for thread API in java
- name and priority are 2 commonly referred properties
- static methods
  - currentThread( )- get a reference of the current running thread
  - sleep(n) - will pause the thread for n millis, can throw InterruptedException when some other thread wants to  interrupt this when it is sleeping
- 2 ways to create a Thread 
  - instantiate a thread with runnable as constructor argument - used when you dont need to do anything other than `override` run method.
  - Extend the Thread class and instantiate the sub-class - can extend methods other than run as well.
  - Thread started by calling `start` method.  
- non static methods
  - start and run
  - isAlive 
  - join - waits for a thread to finish (Joins `this` thread with current thread)
  - isInterrupted
  - yield
- Concept of interruption - any other method can call interrupt method of a thread to interrupt it.
It is a nice way of telling "can you please stop". 
what should the thread do when it knows someone is asking it to interrupt? -
that is your job as programmer to setup a shared communication with interruptor and interrupted.
how will a program know that it is interrupted , it should call isInterrupted method every so often
or call a method like sleep or yield that throws an InterruptedException when interrupted while that method is running.
What should a program do , when faced with InterruptedException - probably same thing if they had checked the isInterrupted method.  
note there is checkAccess method and concept of permissions to interrupt - but we will have to skip it for this discussion.
- Concept of synchronization - every object has a lock/monitor. when any thread is about to enter any synchronized method
or synchronized block, it will check the monitor of the object (for static, assume there is a box for class memory), 
if there is a thread that is stamped there wait , do not run the synchronized code. 
re-check after sometime if the monitor is free, or rather wait on the monitor to be free. 
Once the monitor is free, the thread will stamp its name in it.
and then will start executing the synchronous code.
 while coming out of the synchronous code, by return or exception , jvm will erase the stamp from the monitor.
this adds a performance overhead. 
block synchronization  
```
synchronized(obj) {

  //some synchronized code waiting on the lock for obj
}
```  
- Concept of wait and notify.  wait() , notify() and notifyAll() are all called from synchrozised code  - they are inherited from Object.
  - wait causes the thread to give-up the monitor and block itself until some other thread calls notify or notify-all for the same object.
  The logic is something like a thread does something and is done - it does not need to do anything unless some-other thread chanegs the state of the locked object. so it gives-up control(releases the lock) and blocks itself till it is woken-up.
  Either by notify or interrupt. it will continue from the point it was waiting when it is awakened. wait is usually written in a loop with a condition for the thread to go into a loop.
  - deadlock is 2 or more threads wait on each other in a circular fashion.
- Idea of  suspend( ), resume( ), or stop( ) is depreciated because they are methods in Thread class can be called by other threads which has no clue what critical thing that
current thread is doing, causing serious problems. concept of wait and notify should be extended to build custom solutions for suspend and stop.
- States of thread
  - Blocked - Waiting to get a lock, standing at the call of sync block/method.
  - New     - A thread whose exec has not begun.
  - Runnable - executing or will execute when it will get access to CPU
  - Terminated - Thread completed execution
  - Time_Waiting - on calling sleep or timeout version of join or wait
  - Waiting - Thread waiting for a join to complete or waiting on wait() to be notified.
  - Thread.sleep does not cause release of locks but calling wait causes the release of lock

##Chapter 12:
- Enumeration - uses keyword enum - but it is basically a class that extends the standard Enum class .  
Each of the values in enum are of the type of the specifc enum class - they are constants (public, static , final)  
  enum being classes, can have constructors with arguments. enum can have fields and methods. 
  Concept of enum is very powerful in Java - it is not just a list of predefined values.
- Type wrappers: Character , Boolean , Numeric wrappers:
   - Constructor(premitive/string) , static valueOf(premitive/string)  , premitive xxxxValue()
   - All numeric wrappers have xxxValue method of all numeric types - not just for their TYpe  
   Constructor and valueOf method is only for their type
   - Java will box and unbox premitive to object and back to premitive depending on what is expected.
   - But use needs to be used in that occasion and rely on autobox/unbox only for conversion.  
   It is especially bad to declare everything as wrappers and let java keep boxing and unboxing
- Annotation is a way to stick labels to java code elements - Class, Constructor, Methods, Fields , Variables , another annotation etc...
virtually anything in the code
- The label is designed/created using the syntax

	@interface MyAnnot {
	    int value ();
	    String desc () default "";
	}

- The label has following things
  - What are the bits of data on the Label
  - When is the label discarded
  - On what items can this label be stuck
  - Can multiple labels of this type be stuck on the same element.
- Only uses for reflection apart from IDE and DEbugger are - dynamic extensions - to be used with care as they have performance overhead.
- Reflection API get hold of a class and then all its components. Then it can dig further and look for annotations within that.
- To look for a particular annotated class in a Jar, Java has to read all the loaded classes and find out which one is annotated. But this need not be in memory.  
Java loaders can even read the class files and find it out - there are interesting ways to do this.  
As application developers, for now it should be sufficient to just understand how a particular annotation works.  

##Chapter 13:
- Try with resources. The resource should implement the interface AutoClosable. All classes in java.io implement that interface. 
Hence IO interfaces can be used with try with resource.
We can also define custom resource and use them in this fashion - this approach takes away the manual task of closing the resources in a try catch block.
  try(resource-spec) {
    //use the resource
  }
- instanceOf operator returns a boolean - of the type : objref instanceof type
- Assert takes a boolean condition - an AssertionError is thrown if it is evaluated to false
  - Assert also has a second form that takes a String that is used as message for the alert
  - assert <bool condition> or assert <bool condition>:<String expression>
  - Compact profiles, introduced in Java SE 8, define subsets of the Java SE Platform API that can reduce the static size of the Java runtime on devices that have limited storage capacity.
  - compact1 - smallest run-time , compact2 - little bigger with jdbc and rni, compact3 - security features. - full SE profile includes awt, swing etc...

##Chapter 14:
- generics - If there is a single class that can operate generically over different types(restricted or unrestricted) of Object and returns those objects.  
 Generics elevates the need to operate at Object level by create typed variety of the generic object.
 Generics can also impose validation on generic types that can be given as input - with that the generic class is aware of the some details of the type of Object that would be passed to it as runtime.
  - Generics also support inheritence and interfaces 
  - We can imagine Generics spinning new variants of the class and methods then invoked with types - but this is only for understanding. In the implementation, erasure replaces all these with casts.
  - How it helps in case of MiddleWare? Every middleware accepts a message which can be implemented differently based on if it is XML or json but it might contain generic fields and ways to access that field. 
  BUt at the end, that MIddleWare would not want to forward a generic message but a specific version it received - like JSONMessage
  - generic class is defined like `public class GenClass<T> {}`
  - Types can be bound like `GenClass<T extends A>` , there can be multiple bounds like `GenClass<T extends A & B>` where 1 could be a class and rest interfaces  
  - Note that class `A` can be a generic itself , like `GenClass<T extends List<String>>`
  - We can have a Generic method on a Non-Generic class- static as well as non-static - Generic method syntax - `<access-mod> <T> <rettype> methodName(T t){`  
  Type declaration of a method can also be bound
  - Generics cant use primitives
  - Generics of different types are usually not assignable to each other - even if type themselves are assignable
      Generic subtypes have to be explicitly defined to be subclass of each other like `class List<T> extends Set<T> {`
  - Generic can have more than  1 type parameter  like <T , V>
  - By convention, types are names 
      E - Element (used extensively by the Java Collections Framework)
      K - Key
      N - Number
      T - Type
      V - Value
      S,U,V etc. - 2nd, 3rd, 4th types
  - Type inference for generics. - Java can infer the type from return value assignment variable, type of argument passed to method or constructors.
  - Generics behind the scenes do dynamic casting, they store the types passed during Generic references in variables and use them to cast them as required.
    - Dynamic casting tools - `Class.isInstance(Object)` and `Class.cast(Object)`
  - Wildcard generics , generics when used in a way not really specific to any type can make use of `?` wildcard.  
  Example  "Class<?>" is widely used as none of the methods in Class rely on the Type.  
  Similary it is common to use List<?>. We can bind the wild card like `SomeClass<? extends Number>` , we can have lower bound like `SomeClass< ? super Integer>`  
  Read `<? extends Number>` as some type that extends a Number. `<? super Integer>` read as some type that is parent of Integer.
  
  - Wild card generics can be used to create hierarchy - `A<? extends Integer>` automatically becomes a sub-class of `A<? extends Number>` and both are sub-class of `A<?>`
  - Diamond operator introduced in Java7 enables us to not retype the generic declaration again and compiler will infer it from the assignment and arguments.
  
##Chapter 15
- Lambda expressions is essentially an anonymous method - they create an anonymous class. They cannot be executed on it own. We always need a interface using which this LE is assigned to variables or passed as parameters.  
- Lambda is also called closures. interface used to handle LE are called function interfaces or SAM interface (SIngle Abstract Method).
- SAM interface can be generic
- Lambda expression can throw checked and unchecked exception. But in case of checkedexception, it should  be compatible with throws clause of SAM
- LE can access static and non-static members of the class. but when it uses local variables, the local variables are considered final. thus `this` can be used in LE.
- LE syntax : (parameters)=> { `expr` }. parameters - ( `<datatype1> var1` , `<datatype2> var2` ...)
- LE datatype can also be inferred. so datatype is not necessary if LE is assigned to SAM.
- Method references are similar to LE in that they also require a target type that is SAM interface.
- Syntax for static method reference `ClassName::methodName` , non-static method reference `obj::methodName`.
- There are some native JVM tweaks to support(improve performance) lambda but they are majorly syntax improvements passing functions as object references.
- Java provides very important SAM interfaces out of the box and should be used as much as possible.Main ones being  . 
   1. Consumer - Takes a Type and returns void. has **`apply`** and `andThen`
   2. Supplier - Takes void and returns a Type. has **`get`**
   3. Function - Takes Type and returns a Type. has `andThen` (applies this and given-arg) , `compose` (applies given-arg and this), **`apply`** and `identity` .
   4. Predicate - Takes a type and returns a boolean - it is basically a filter. has **`test`** , `and` , `or` , `negate` and static methods `isEqual` and `not`.
- Deciding to SKIP Modules functionality for NOW.

##Chapter 16 - Java Modules introduced in Java9
- Java modules capability can basically group packages, declare dependecnies and usages between them. 
- it can also selectively expose the components in them to other modules.
- This makes it easier to manage a large project at one higher level - module
- But most powerfull use of this is - jdk itself is grouped into modules, we can create a new JRE based on modules we require using the new module tools.  
Those restricted JRE can be as small as 35mb compared to full JDK which is about 450mb
- Modules is described by module descripter , which is a java file based in the root folder of our packages
An example of a module definition
```
module com.mydeveloperplanet.jpmshello {
    requires java.base;
    requires java.xml;
    requires com.mydeveloperplanet.jpmshi;
}
```
- Java provides tools to compile and make jar out of modules
- For example , `https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-9-jigsaw/src/modules`
  - com.baeldung.student.client
      - module-info.java - This is Module 1
      - com
        - baeldung
          - student
            - client
  - com.baeldung.student.server
      - module-info.java - This is Module 2
      - com
        - baeldung
          - student
            - server
- For more details, refer -  https://www.baeldung.com/java-9-modularity

##Chapter 17 - String
- Some basics I documented in https://jaganshome.blogspot.com/2020/08/java-interview-basics.html
- String(immutable) , StringBuffer(Synchronized-mutable) , StringBuilder(Synchronized-mutable), all of them extend CharSequence.
- `String(char chars[ ], int startIndex, int numChars)` - interesting constructor
- String has length() method.
- regionMatches( ) can match a String in specific region of a String.
- compareTo( ) uses dictionary ordering.
- trim can remove only spaces from begin and end
- strip can remove all whitespaces, using  stripLeading( ) and stripTrailing( ) - we can control which whitespace to remove.
- NO built in Padding method exist
- static String valueOf in String takes many different arguments and basically invoke the toString method of the argument.
- toUpperCase, toLowerCase and join methods are available
- reverse( ) method is available in StringBuffer

##Chapter 18 - Exploring Java lang
- java.lang is part of the java.base module (java9)
- typewrapper and few common utils are discussed.
- Clonable interface - Only classes marked with this interface can be clonned. Clone method inside object is protected and simply does byte copy.  
  Class implementing Clonnable should override clone method and make it public (if to be called from outside) and handle all class variables that hold references, either create another object or decide to use the references.
- java comes with facility to embed and call scripts - java , ruby , python and groovy
- java has package - java.lang.invoke that can be used to build dynamic features. can find and execute methods dynamically.
- java.lang.reflect, can get Info about java code structures at run time.
- java.lang.ref - gives more options on garbage collections.
- java.lang.instrument - can add more instrumentation to Java.
- Review the image on top of ch-18 of java complete reference.

##Chapter 19 - The collections framework
- java.util is part of the java.base module.
- Collections are implemented using `interfaces`
- Algorithms are implemented as static methods on Collections class.
- Iterator interface offers a general-purpose, standardized way of accessing the elements within a collection.
- Spliterator support parallel iteration.
- PrimitiveIterator and PrimitiveIterator.OfDouble - supports premitive operations.
- Map is strictly not a collection but often used along with collections.
- Important interfaces
  - Collection
  - Deque
  - List
  - NavigatableSet
- New concept in Map interface, compute , computeIfPresent, computeIfAbsent
  - compute takes a key and function that takes 2 arguments(key and value) and returns a new value. Basically we give a function that can compute the new value given old key-value for a given-key.  
  that new value is placed in the Map and returned as well.
  like `<new-computed value>  = map.compute(give-key, (keyvar, valuevar)-> {do some computation with args and retuen a new val} )`  
  if it returns a null, value is removed from the Map if present. if not null value is added or replaced.
- `computeIfAbsent` function triggers only if value not there(else value available is returned), function value is added if return value is not null, else do nothing
- `computeIfPresent` function triggers only if value is there, update if not null, remove if null.
- of method is often used to create a small Set, Map etc... is a very handy util

##Chapter 20 - Other util methods
- There are ways to refer method references, static, object-method and arbitory reference to instance method
- Option is very powerful for handling nulls in functional programming. But it also supports functions like map and filter.
- lot of Function Interfaces documented in this chapter, all of them are very meaningful.

##Chapter 21 - Exploring java.io
- This talks about Stream(Binary) and Reader and Writer(Text) IO classes.
- AutoClosable Interface , `File` handling etc...
- Serializable interface , Object streams also discussed

##Chapter 22 - Exploring java.nio
- It is a new design of IO but older java.io is not deprecated yet. built on two foundational items: buffers and channels.
- Buffer holds data and channel represents a connection
- Path interface refers a File path and is much more powerfull like path split, monitoring the file etc...

#Chapter 23 - Networking
- InetAddress can encapsulate both ip4 and ip6
- TCP IP socket and related libraries are discussed
- JAVA.NET.HTTP introduces helpers for handling HTTP requests into Java

#Chapter 24 - is about awt event handling(UI)
- Not required

#Chapter 25 - about awt itself (UI)
- Not required

#Chapter 26 - about awt layout and controls.(UI)
- Not required

#Chapter 27 - Images handling and display
- Not required

#Chapter 28 - The Concurrency Utilities
- Revise threads
- 
- Spend some time on this URL 
  https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/




  
  




