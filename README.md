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

### The Syntax

- If you're just getting started: 

The best way to start writing Java Assembler is by having a look at 
the Java Bytecode Outline plugin for Eclipse or Intellij Idea IDE. 
Write a small class in Java Sourcecode and understand the generated Java Bytecode Outline.

- If you want to know about the Syntax: 

TODO:

Let's take a look at this example Class called Demo: 

    .COMPUTE_MAXS

    .bytecode #V1_7
    .access #ACC_PUBLIC
    .name #Demo
    .supername #java/lang/Object
    .classVisit

    .FIELDACC #ACC_PRIVATE
    .FIELDVISIT #I #var1

    .methodAccess #ACC_PUBLIC
    .methodName #<init>
    .methodDesc #(I)V
    .methodVisit
        ALOAD 0
        INVOKESPECIAL #java/lang/Object #<init> #()V
        ALOAD 0
        ICONST_0
        PUTFIELD #Demo #var1 #I
        ALOAD 0
        ILOAD 1
        PUTFIELD #Demo #var1 #I
        RETURN
    .visitMaxs 2 2
    .methodEnd

The Syntax is devided into `.COMPUTE_MAX`

## 3. Developer Guide

The project is developed using Maven and the Intellij Idea IDE. The 

## 4. Credits
The project started as a studen project suggested by the CITI Lab at the INSA Lyon, 
planed and implemented by Benjamin Barzen, 3rd year student at the TU Berlin.

