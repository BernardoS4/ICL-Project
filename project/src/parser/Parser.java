/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
package parser;
import java.util.Map;
import java.util.HashMap;
import ast.*;

/** ID lister. */
public class Parser implements ParserConstants {

  static final public ASTNode Start() throws ParseException {ASTNode t;
    t = Seq();
    jj_consume_token(DOUBLESCN);
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Seq() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Affect();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SCN:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      op = jj_consume_token(SCN);
      t2 = Affect();
t1 = new ASTSeq(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Affect() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = BoolAdd();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AFFECTATION:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      op = jj_consume_token(AFFECTATION);
      t2 = BoolAdd();
t1 = new ASTAffect(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode BoolAdd() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = BoolMul();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      op = jj_consume_token(OR);
      t2 = BoolMul();
t1 = new ASTOr(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode BoolMul() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = EqMult();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
      op = jj_consume_token(AND);
      t2 = EqMult();
t1 = new ASTAnd(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode EqMult() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Rel();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQUALS:
      case DIFF:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQUALS:{
        op = jj_consume_token(EQUALS);
        break;
        }
      case DIFF:{
        op = jj_consume_token(DIFF);
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Rel();
if (op.kind == EQUALS)
                      t1 = new ASTEqual(t1,t2);
                    else
                      t1 = new ASTDiff(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Rel() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Exp();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case GREATER:
    case LOWER:
    case LEQUAL:
    case GEQUAL:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case GREATER:{
        op = jj_consume_token(GREATER);
        break;
        }
      case LOWER:{
        op = jj_consume_token(LOWER);
        break;
        }
      case LEQUAL:{
        op = jj_consume_token(LEQUAL);
        break;
        }
      case GEQUAL:{
        op = jj_consume_token(GEQUAL);
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Exp();
if (op.kind == GREATER)
                      t1 = new ASTGreater(t1,t2);
                    if (op.kind == LOWER)
                      t1 = new ASTLower(t1,t2);
                    if (op.kind == LEQUAL)
                      t1 = new ASTLequals(t1,t2);
                    else
                      t1 = new ASTGequals(t1,t2);
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Exp() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Term();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        op = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        op = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
if (op.kind == PLUS)
                         t1 = new ASTPlus(t1,t2);
                    else
                      t1 = new ASTSub(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Term() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Fact();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TIMES:
    case DIV:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:{
        op = jj_consume_token(TIMES);
        break;
        }
      case DIV:{
        op = jj_consume_token(DIV);
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
if(op.kind == TIMES)
            t1 = new ASTTimes(t1, t2);
      else  t1 = new ASTDiv(t1, t2);
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Fact() throws ParseException {Token n;
  ASTNode t;
  Map<String,ASTNode> m;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NUM:{
      n = jj_consume_token(NUM);
t = new ASTNum(Integer.parseInt(n.image));
      break;
      }
    case Id:{
      n = jj_consume_token(Id);
t = new ASTId(n.image);
      break;
      }
    case TRUE:
    case FALSE:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TRUE:{
        n = jj_consume_token(TRUE);
        break;
        }
      case FALSE:{
        n = jj_consume_token(FALSE);
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
t = new ASTBool(Boolean.parseBoolean(n.image));
      break;
      }
    case LPAR:{
      jj_consume_token(LPAR);
      t = Exp();
      jj_consume_token(RPAR);
      break;
      }
    case MINUS:{
      jj_consume_token(MINUS);
      n = jj_consume_token(NUM);
t = new ASTNum(Integer.parseInt(n.image));
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      t = Fact();
t = new ASTNot(t);
      break;
      }
    case NEW:{
      jj_consume_token(NEW);
      t = Fact();
      break;
      }
    case DEREF:{
      jj_consume_token(DEREF);
      t = Fact();
      break;
      }
    case PRINT:{
      jj_consume_token(PRINT);
      t = Fact();
      break;
      }
    case IF:{
      jj_consume_token(IF);
      t = Seq();
      jj_consume_token(LBRA);
      t = Seq();
      jj_consume_token(RBRA);
      jj_consume_token(LBRA);
      t = Seq();
      jj_consume_token(RBRA);
      break;
      }
    case WHILE:{
      jj_consume_token(WHILE);
      t = Seq();
      jj_consume_token(LBRA);
      t = Seq();
      jj_consume_token(RBRA);
      break;
      }
    case LBRA:{
      jj_consume_token(LBRA);
m       =       new     HashMap();
      label_7:
      while (true) {
        jj_consume_token(DEF);
        n = jj_consume_token(Id);
        jj_consume_token(EQ);
        t = Exp();
        jj_consume_token(SCN);
m.put(n.image,t);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DEF:{
          ;
          break;
          }
        default:
          jj_la1[13] = jj_gen;
          break label_7;
        }
      }
      t = Exp();
      jj_consume_token(RBRA);
t =     new     ASTDef( m,      t       );
      break;
      }
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[15];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x2000000,0x0,0x10000000,0x8000000,0x0,0x0,0x60000000,0x60000000,0x18000,0x18000,0x60000,0x60000,0x60,0x200000,0x804979e0,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x10,0x0,0x0,0xc,0xc,0x3,0x3,0x0,0x0,0x0,0x0,0x0,0x0,0x20,};
	}

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[39];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 15; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 39; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
