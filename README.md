# The project is still under construction and not yet ready for open colaboration.
# JavaByteC
A Java Assembler based on the OW2 ASM framework

## 1. Introduction

This project uses the ANTLR4 and OW2 ASM Framework to provide a simple to use way to create Java classes from Java Assembler.
If you spend your time creating your Java classes with OW2 ASM or compiling from Java Sourcecode, it just got much easier. 
Use the JavaByteC Assembler to generate classes from textfiles or in-code Java strings. 

## 2. User Guide
To download and use the compiler go to the **target** folder and download the newest jar file with included dependencies. 

### The API

- If you want to use the jar as standalone using the command line you can call the assembler as follows:

`java -cp [PATH TO DOWNLOADED JAR] javabytec.assembler.AssemblerAPI [PATH TO ASSEMBLER-TEXTFILE] [PATH TO OUTPUT CLASS FILE]`

Example: 

`java -cp JavaByteC-0.1-SNAPSHOT-jar-with-dependencies.jar javabytec.assembler.AssemblerAPI /home/ben/JavaByteC/examples/trycatch.code.demo.asm ./Demo.class`

- If you want to use the Assembler in your own java code, the API provides multiple methods to accomplish this: 

1. `byte[] stringToBytecode(String inputString)` 

  - formated Assembler as String -> returns bytecode as array

2. `void stringToBytecodeFile(String inputString, String outputPath)`

  - formated Assembler as String -> File at the specified location

3. `byte[] fileToBytecode(String inputPath)`

  - file as specified by path -> returns bytecode as array

TODO: Insert Javadoc Here

### Writing Assembler Code

#### Getting Started

The best way to start writing Java Assembler is by having a look at 
the Java Bytecode Outline plugin for Eclipse or Intellij Idea IDE. 
Write a small class in Java Sourcecode and understand the generated Java Bytecode Outline.

#### The Syntax

The Syntax consists of following elements: 

Element | Expression
------------ | -------------
10, 10.0 | Bare Numbers
{ } | Arrays. Note: Parameters in Arrays are not escaped by hastags.
" " | Strings
; | Line-Comments
ICONST_0 | Instructions
#PARAM | Parameters (excluding Arrays, bare Numbers and Strings)
@INSN | Annotation
.DIRECTIVE | Directive to Assembler, Parameters
LABEL #NAME | Labels. Labelnames always need to start with a #Hashtag

**In general, everything can be written in lowercase, camelCase or UPPERCASE.** 

Let's take a look at this example Class called Demo:

It's the simple Hello World class with a main method which prints "Hello World!" to the standard output.


    .COMPUTE_MAXS
    
    .bytecode #V1_7
    .access #ACC_PUBLIC
    .name #Demo
    .supername #java/lang/Object
    .classVisit

    .methodAccess #ACC_PUBLIC
    .methodName #<init>
    .methodDesc #(I)V
    .methodVisit
        ALOAD 0
        INVOKESPECIAL #java/lang/Object #<init> #()V
        RETURN
    .visitMaxs 1 1
    .methodEnd

    .methodAccess #ACC_PUBLIC #ACC_STATIC
    .methodName #main
    .methodDesc #([Ljava/lang/String;)V
    .methodVisit
        GETSTATIC #java/lang/System #out #Ljava/io/PrintStream;
        LDC #[Ljava/lang/String; "Hello World!"
        INVOKEVIRTUAL #java/io/PrintStream #println #(Ljava/lang/String;)V
        RETURN
    .visitMaxs 2 1
    .methodEnd

    .end

[Hint: Since the assembler works with the OW2 ASM Framework it might be usefull to look at their javadoc of MethodVisitor and ClassWriter to understand the true significance of some parameters.](http://asm.ow2.org/asm50/javadoc/user/org/objectweb/asm/ClassWriter.html) 

##### Creating a Class

Every Class in Assembler starts with either the `.COMPUTE_MAXS` or the `.COMPUTE_FRAMES` Flag. It creates the outline of the class. The `COMPUTE_MAXS` Flag *automatically compute the maximum stack size and the maximum number of local variables of methods.*, the `COMPUTE_FRAMES` Flag *automatically compute the stack map frames of methods*. 

This is followed by a section which describes the Class: 

Element | Meaning
------------ | -------------
.bytecode #V1_7 | Set Java Version. Both Integers and Version Tags are supported.
.access #ACC_PUBLIC | Access flags of the class. You can add as many as you wish.
.name #Demo | The fully qualified class name of the class, seperated by '/'. 
You can also dismiss this line and add this parameter to the .classVisit Instruction.
.supername #java/lang/Object | The fully qualified class name of the superclass. 
.classVisit | Finish the description of the class and start with fields & methods. 
.end | End the class. 

Hint: Nested classes are not supported at this point. 

##### Methods

Creating a method is similar to creating classes. 

Element | Meaning
------------ | -------------
.methodAccess #ACC_PUBLIC | Access Flags, bare numbers supported.
.methodName #<init> | Method Name. Constructor is always called <init>.
.methodDesc #(I)V | Method Descriptor.
.methodVisit  | Finish method description and start with instructions.
.methodVisit #<init> #(I)V | Name and descriptor can also be added here.
.visitMaxs 1 1 | Set MAXSTACK and MAXLOCALS (in that order). 
.methodEnd | End this method. All label names get reset. 

##### Instructions

A comprehensive instruction list can be found on [Wikipedia](https://en.wikipedia.org/wiki/Java_bytecode_instruction_listings).
Parameters are added with #Hashtags, exceptions are Integers and Floats, which are bare, and arrays and strings which are started / ended by {brackets} or "quotes" respectively. Most instructions work the way Java Bytecode is usually visiualized, but there are some exceptions: 

Ref | Element | Syntax
--- | ------------ | -------------
1 | FRAME FULL [I geometry/testclass] [java/io/UnsupportedEncodingException] | FRAME #FULL 2 {.INTEGER Demo} 1 {java/io/UnsupportedEncodingException}


1. FRAME: 
The Framestate must be escaped with a #hashtag, typenames are put in an array and written as .INTEGER, .FLOAT, etc..
Before each Array the length must be specified. For details refer to the [OW2 ASM Javadoc](http://asm.ow2.org/asm50/javadoc/user/org/objectweb/asm/MethodVisitor.html#visitFrame-int-int-java.lang.Object:A-int-java.lang.Object:A-)

TODO: Complete this list. 

##### Fields

Fields are added as follows below the `.classVisit` statement: 

    .FIELDACC #ACC_PRIVATE
    .FIELDVISIT #I #var1

##### Annotations

TODO
   
## 3. Developer Guide

The project is developed using Maven and the Intellij Idea IDE. The 

## 4. Credits
The project started as a studen project suggested by the CITI Lab at the INSA Lyon, 
planed and implemented by Benjamin Barzen, 3rd year student at the TU Berlin.

