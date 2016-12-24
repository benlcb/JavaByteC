grammar JavaByteGrammar;

code
    : line* EOF
    ;

line
    : comment
    | instruction
    | directive
    | EOL
    ;

directive
        : constdir (PARAM+)?
        ;

instruction: opcode (PARAM+)?;

opcode
    : insn
    | fieldinsn
    | methodinsn
    | varinsn
    | ldcinsn
    | typeinsn
    | fieldop
    | localvariable
    | jumpInsn
    | label
    | lineNumber
    | localVariableAnnotation
    | lookupSwitchInsn
    | tableSwitchInsn
    | tryCatchBlock
    | frame
    | intinsn
    | multianewarray
    | iincinsn
    | defaultAnnotation
    | methodAnnotation
    | typeAnnotation
    | parameterAnnotation
    | insnAnnotation
    | trycatchAnnotation
    | localvariableAnnotation
    ;

comment
      : LINE_COMMENT
      ;

constdir
        : COMPUTEFRAMES
        | COMPUTEMAXS
        | BYTECODE
        | NAME
        | ACCESS
        | SIGNATURE
        | SUPERNAME
        | INTERFACES
        | CLASSVISIT
        | END
        | METHODACCESS
        | METHODNAME
        | METHODDESC
        | METHODSIGN
        | METHODEXCEP
        | METHODEND
        | METHODVISIT
        | VISITMAXS
        | SETDEFAULT  
        ;

defaultAnnotation
    : DEFAULTANNOTATION
    ;

methodAnnotation
    : METHODANNOTATION
    ;

typeAnnotation
    : TYPEANNOTATION
    ;

parameterAnnotation
    : PARAMETERANNOTATION
    ;

insnAnnotation
    : INSNANNOTATION
    ;

trycatchAnnotation
    : TRYCATCHANNOTATION
    ;

localvariableAnnotation
    : LOCALVARIABLEANNOTATION
    ;

insn
    : INSN
    ;

fieldinsn
    : FIELDINSN
    ;

methodinsn
    : METHODINSN
    ;

varinsn
    : VARINSN
    ;

ldcinsn
    : LDCINSN
    ;

typeinsn
    : TYPEINSN
    ;


fieldop
    : FIELDVISIT
    | FIELDACC
    ;

localvariable
    : LOCALVARIABLE
    ;

jumpInsn
    : JUMPINSN
    ;

label
    : LABEL
    ;

lineNumber
    : LINENUMBER
    ;

localVariableAnnotation
    : LOCALVARIABLEANNOTATION
    ;

lookupSwitchInsn
    : LOOKUPSWITCHINSN
    ;

tableSwitchInsn
    : TABLESWITCHINSN
    ;

tryCatchBlock
    : TRYCATCHBLOCK
    ;

frame
    : FRAME
    ;

intinsn 
    : INTINSN
    ;

iincinsn
    : IINCINSN
    ;

multianewarray
    : MULTIANEWARRAY
    ;

TYPEINSN
        : NEW
        | ANEWARRAY
        | CHECKCAST
        | INSTANCEOF
        ;

PARAM
    : I
    | D
    | STR
    | ARG
    | ARRAY
    ;


DEFAULTANNOTATION
    : '@default'
    | '@DEFAULT'
    ;

METHODANNOTATION
    : '@method'
    | '@METHOD'
    ;

TYPEANNOTATION
    : '@type'
    | '@TYPE'
    ;

PARAMETERANNOTATION
    : '@parameter'
    | '@PARAMETER'
    ;

INSNANNOTATION
    : '@insn'
    | '@INSN'
    ;

TRYCATCHANNOTATION
    : '@trycatch'
    | '@TRYCATCH'
    ;

LOCALVARIABLEANNOTATION
    : '@localvariable'
    | '@LOCALVARIABLE'
    ;

TABLESWITCHINSN
    : 'TABLESWITCHINSN'
    | 'tableSwitchInsn'
    | 'tableswitchinsn'
    ;

LOOKUPSWITCHINSN
    : 'LOOKUPSWITCHINSN'
    | 'lookupSwitchInsn'
    | 'lookupswitchinsn'
    ;

MULTIANEWARRAY
    : 'MULTIANEWARRAY'
    | 'multiANewArray'
    | 'multianewarray'
    ;

IINCINSN
    : 'IINC'
    | 'iInc'
    | 'iinc'
    ;


INTINSN 
    : 'BIPUSH'
    | 'biPush'
    | 'bipush'
    | 'SIPUSH'
    | 'siPush'
    | 'sipush'
    | 'NEWARRAY'
    | 'newArray'
    | 'newarray'
    ;

FRAME
    : 'FRAME'
    | 'frame'
    ;

TRYCATCHBLOCK
    : 'TRYCATCHBLOCK'
    | 'tryCatchBlock'
    | 'trycatchblock'
    ;

LABEL
    : 'LABEL'
    | 'label'
    | 'l'
    ;

LINENUMBER
    : 'LINENUMBER'
    | 'lineNumber'
    | 'linenumber'
    ;

JUMPINSN
    : 'IFEQ'
    | 'ifeq'
    | 'IFNE'
    | 'ifne'
    | 'IFLT'
    | 'iflt'
    | 'IFGE'
    | 'ifge'
    | 'IFGT'
    | 'ifgt'
    | 'IFLE'
    | 'ifle'
    | 'IF_ICMPEQ'
    | 'if_icmpeq'
    | 'IF_ICMPNE'
    | 'if_icmpne'
    | 'IF_ICMPLT'
    | 'if_icmplt'
    | 'IF_ICMPGE'
    | 'if_icmpge'
    | 'IF_ICMPGT'
    | 'if_icmpgt'
    | 'IF_ICMPLE'
    | 'if_icmple'
    | 'IF_ACMPEQ'
    | 'if_acmpeq'
    | 'IF_ACMPNE'
    | 'if_acmpne'
    | 'GOTO'
    | 'goto'
    | 'JSR'
    | 'jsr'
    | 'IFNULL'
    | 'ifnull'
    | 'ifNull'
    | 'IFNONNULL'
    | 'ifnonnull'
    | 'ifNonNull'
    ;
    
NEW
    : 'NEW'
    | 'new'
    ;

ANEWARRAY
    : 'ANEWARRAY'
    | 'anewarray'
    | 'aNewArray'
    ;

CHECKCAST 
    : 'CHECKCAST' 
    | 'checkcast' 
    | 'checkCast'
    ;

INSTANCEOF 
    : 'INSTANCEOF' 
    | 'instanceof' 
    | 'instanceOf'
    ;

FIELDVISIT
    : '.fieldvisit'
    | '.fieldVisit'
    | '.FIELDVISIT'
    ;

FIELDACC
    : '.fieldAcc'
    | '.FIELDACC'
    | '.fieldacc'
    ;


COMPUTEFRAMES
            : '.COMPUTE_FRAMES' 
            | '.compute_frames' 
            | '.computeFrames' 
            ;

COMPUTEMAXS
        : '.COMPUTE_MAXS'
        | '.compute_maxs'
        | '.computeMax'
        ;

BYTECODE
        : '.bytecode'
        | '.byteCode'
        | '.BYTECODE'
        ;

NAME
        : '.name'
        | '.NAME'
        ;

ACCESS
        : '.access'
        | '.ACCESS'
        ;

SIGNATURE
        : '.signature'
        | '.SIGNATURE'
        ;

SUPERNAME
        : '.supername'
        | '.superName'
        | '.SUPERNAME'
        ;

INTERFACES
        : '.interfaces'
        | '.INTERFACES'
        ;

CLASSVISIT
        : '.classvisit'
        | '.classVisit'
        | '.CLASSVISIT'
        ;

END
        :  '.end'
        | '.END'
        ;

METHODACCESS
        : '.methodaccess'
        | '.methodAccess'
        | '.METHODACCESS'
        ;

METHODNAME
        : '.methodname'
        | '.methodName'
        | '.METHODNAME'
        ;

METHODDESC
        : '.methoddesc'
        | '.methodDesc'
        | '.METHODDESC'
        ;

METHODSIGN
        : '.methodsign'
        | '.methodSign'
        | '.METHODSIGN'
        ;

METHODEXCEP
        : '.methodexcep'
        | '.methodExcep'
        | '.METHODEXCEP'
        ;
METHODEND
        : '.methodend'
        | '.methodEnd'
        | '.METHODEND'
        ;

METHODVISIT
        : '.methodvisit'
        | '.methodVisit'
        | '.METHODVISIT'
        ;

VISITMAXS
        : '.visitmaxs'  
        | '.visitMaxs'  
        | '.VISITMAXS'
        ;  
SETDEFAULT
        : 'setdefault'
        | 'setDefault'
        | 'SETDEFAULT'
        ;

VARINSN
      : 'ALOAD'
      | 'aload'
      | 'ILOAD'
      | 'iload'
      | 'LLOAD' 
      | 'lload' 
      | 'FLOAD' 
      | 'fload' 
      | 'DLOAD' 
      | 'dload' 
      | 'ALOAD' 
      | 'aload' 
      | 'ISTORE' 
      | 'istore' 
      | 'LSTORE' 
      | 'lstore' 
      | 'FSTORE' 
      | 'fstore' 
      | 'DSTORE' 
      | 'dstore' 
      | 'ASTORE'
      | 'astore'
      ;

INSN
    : 'iadd'
    | 'IADD'
    | 'istore'
    | 'ISTORE'
    | 'return'
    | 'RETURN'
    | 'aaload'
    | 'AALOAD'
    | 'aasotre'
    | 'AASOTRE'
    | 'aconst_null'
    | 'ACONST_NULL'
    | 'aload_0'
    | 'ALOAD_0'
    | 'aload_1'
    | 'ALOAD_1'
    | 'aload_2'
    | 'ALOAD_2'
    | 'aload_3'
    | 'ALOAD_3'
    | 'areturn'
    | 'ARETURN'
    | 'arraylength'
    | 'ARRAYLENGTH'
    | 'astore_0'
    | 'ASTORE_0'
    | 'astore_1'
    | 'ASTORE_1'
    | 'astore_2'
    | 'ASTORE_2'
    | 'astore_3'
    | 'ASTORE_3'
    | 'athrow'
    | 'ATHROW'
    | 'baload'
    | 'BALOAD'
    | 'bastore'
    | 'BASTORE'
    | 'breakpoint'
    | 'BREAKPOINT'
    | 'caload'
    | 'CALOAD'
    | 'castore'
    | 'CASTORE'
    | 'd2f'
    | 'D2F'
    | 'd2i'
    | 'D2I'
    | 'd2l'
    | 'D2L'
    | 'dadd'
    | 'DADD'
    | 'daload'
    | 'DALOAD'
    | 'dastore'
    | 'DASTORE'
    | 'dcmpg'
    | 'DCMPG'
    | 'dcmpl'
    | 'DCMPL'
    | 'dconst_0'
    | 'DCONST_0'
    | 'dconst_1'
    | 'DCONST_1'
    | 'ddiv'
    | 'DDIV'
    | 'dload_0'
    | 'DLOAD_0'
    | 'dload_1'
    | 'DLOAD_1'
    | 'dload_2'
    | 'DLOAD_2'
    | 'dload_3'
    | 'DLOAD_3'
    | 'dmul'
    | 'DMUL'
    | 'dneg'
    | 'DNEG'
    | 'drem'
    | 'DREM'
    | 'dreturn'
    | 'DRETURN'
    | 'dstore_0'
    | 'DSTORE_0'
    | 'dstore_1'
    | 'DSTORE_1'
    | 'dstore_2'
    | 'DSTORE_2'
    | 'dstore_3'
    | 'DSTORE_3'
    | 'dsub'
    | 'DSUB'
    | 'dup'
    | 'DUP'
    | 'dup_x1'
    | 'DUP_X1'
    | 'dup_x2'
    | 'DUP_X2'
    | 'dup2'
    | 'DUP2'
    | 'dup2_x1'
    | 'DUP2_X1'
    | 'dup2_x2'
    | 'DUP2_X2'
    | 'f2i'
    | 'F2I'
    | 'f2d'
    | 'F2D'
    | 'f2l'
    | 'F2L'
    | 'fadd'
    | 'FADD'
    | 'faload'
    | 'FALOAD'
    | 'fastore'
    | 'FASTORE'
    | 'fcmpg'
    | 'FCMPG'
    | 'fcmpl'
    | 'FCMPL'
    | 'fconst_0'
    | 'FCONST_0'
    | 'fconst_1'
    | 'FCONST_1'
    | 'fconst_2'
    | 'FCONST_2'
    | 'fdiv'
    | 'FDIV'
    | 'fload_0'
    | 'FLOAD_0'
    | 'fload_1'
    | 'FLOAD_1'
    | 'fload_2'
    | 'FLOAD_2'
    | 'fload_3'
    | 'FLOAD_3'
    | 'fmul'
    | 'FMUL'
    | 'fneg'
    | 'FNEG'
    | 'frem'
    | 'FREM'
    | 'freturn'
    | 'FRETURN'
    | 'fstore_0'
    | 'FSTORE_0'
    | 'fstore_1'
    | 'FSTORE_1'
    | 'fstore_2'
    | 'FSTORE_2'
    | 'fstore_3'
    | 'FSTORE_3'
    | 'fsub'
    | 'FSUB'
    | 'i2b'
    | 'I2B'
    | 'i2c'
    | 'I2C'
    | 'i2d'
    | 'I2D'
    | 'i2f'
    | 'I2F'
    | 'i2l'
    | 'I2L'
    | 'i2s'
    | 'I2S'
    | 'iadd'
    | 'IADD'
    | 'iaload'
    | 'IALOAD'
    | 'iand'
    | 'IAND'
    | 'iastore'
    | 'IASTORE'
    | 'iconst_m1'
    | 'ICONST_M1'
    | 'iconst_0'
    | 'ICONST_0'
    | 'iconst_1'
    | 'ICONST_1'
    | 'iconst_2'
    | 'ICONST_2'
    | 'iconst_3'
    | 'ICONST_3'
    | 'iconst_4'
    | 'ICONST_4'
    | 'iconst_5'
    | 'ICONST_5'
    | 'idiv'
    | 'IDIV'
    | 'iload_0'
    | 'ILOAD_0'
    | 'iload_1'
    | 'ILOAD_1'
    | 'iload_2'
    | 'ILOAD_2'
    | 'iload_3'
    | 'ILOAD_3'
    | 'impdep1'
    | 'IMPDEP1'
    | 'impdep2'
    | 'IMPDEP2'
    | 'imul'
    | 'IMUL'
    | 'ineg'
    | 'INEG'
    | 'ior'
    | 'IOR'
    | 'irem'
    | 'IREM'
    | 'ireturn'
    | 'IRETURN'
    | 'ishl'
    | 'ISHL'
    | 'ishr'
    | 'ISHR'
    | 'istore'
    | 'ISTORE'
    | 'istore_0'
    | 'ISTORE_0'
    | 'istore_1'
    | 'ISTORE_1'
    | 'istore_2'
    | 'ISTORE_2'
    | 'istore_3'
    | 'ISTORE_3'
    | 'isub'
    | 'ISUB'
    | 'iushr'
    | 'IUSHR'
    | 'ixor'
    | 'IXOR'
    | 'l2d'
    | 'L2D'
    | 'l2f'
    | 'L2F'
    | 'l2i'
    | 'L2I'
    | 'ladd'
    | 'LADD'
    | 'laload'
    | 'LALOAD'
    | 'land'
    | 'LAND'
    | 'lastore'
    | 'LASTORE'
    | 'lcmp'
    | 'LCMP'
    | 'lconst_0'
    | 'LCONST_0'
    | 'lconst_1'
    | 'LCONST_1'
    | 'ldiv'
    | 'LDIV'
    | 'lload_0'
    | 'LLOAD_0'
    | 'lload_1'
    | 'LLOAD_1'
    | 'lload_2'
    | 'LLOAD_2'
    | 'lload_3'
    | 'LLOAD_3'
    | 'lmul'
    | 'LMUL'
    | 'lneg'
    | 'LNEG'
    | 'lor'
    | 'LOR'
    | 'lrem'
    | 'LREM'
    | 'lreturn'
    | 'LRETURN'
    | 'lshl'
    | 'LSHL'
    | 'lshr'
    | 'LSHR'
    | 'lstore_0'
    | 'LSTORE_0'
    | 'lstore_1'
    | 'LSTORE_1'
    | 'lstore_2'
    | 'LSTORE_2'
    | 'lstore_3'
    | 'LSTORE_3'
    | 'lsub'
    | 'LSUB'
    | 'lushr'
    | 'LUSHR'
    | 'lxor'
    | 'LXOR'
    | 'monitorenter'
    | 'MONITORENTER'
    | 'monitorexit'
    | 'MONITOREXIT'
    | 'nop'
    | 'NOP'
    | 'pop'
    | 'POP'
    | 'pop2'
    | 'POP2'
    | 'return'
    | 'RETURN'
    | 'saload'
    | 'SALOAD'
    | 'sastore'
    | 'SASTORE'
    | 'swap'
    | 'SWAP'
    ;

LDCINSN
      : 'ldc'
      | 'LDC'
      ;

FIELDINSN
        : 'putfield' 
        | 'PUTFIELD' 
        | 'getfield' 
        | 'GETFIELD' 
        | 'getstatic' 
        | 'GETSTATIC' 
        | 'putstatic' 
        | 'PUTSTATIC' 
        ;

METHODINSN
         : 'invokevirtual'
         | 'INVOKEVIRTUAL'
         | 'invokespecial'
         | 'INVOKESPECIAL'
         | 'invokestatic'
         | 'INVOKESTATIC'
         | 'invokeinterface'
         | 'INVOKEINTERFACE'
         ;

LOCALVARIABLE
                 : 'localvariable'
                 ;

I: [0-9]+ ;

D: I '.' I ;

ARRAY: '{' ~('}')+ '}';

STR: '"' ~('"')+ '"' ;

ARG: '#' ~(' ' | '"' | '\n' | '\r')+ ;

EOL: '\r'? '\n';

WHITESPACE: [ \t\n\r]+ -> skip ;

LINE_COMMENT : ';' ~ ('\n' | '\r')* -> skip;
//MULT_COMMENT : ';*' ~ ('*' | ';')* '*;' -> skip;