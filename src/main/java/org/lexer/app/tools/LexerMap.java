package org.lexer.app.tools;

import java.awt.RenderingHints.Key;

public class LexerMap {

	public class DataPattern {
		public int token;
		public String lexem;

		public DataPattern(int token, String lexem) {
			this.token = token;
			this.lexem = lexem;
		}

		public DataPattern() {

		}

		public void setToken(int token) {
			this.token = token;
		}

		public int getToken() {
			return this.token;
		}

		public void setLexem(String lexem) {
			this.lexem = lexem;
		}

		public String getLexem() {
			return this.lexem;
		}
	}

	public int pointer;
	char[] characters;
	private int counter;
	private int newState;
	private int state;
	private int chr;

	public static final int[][] transitionTable = {
			{ 4, 4, 5, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 25, 19, 35, 8, 1, 26, 27, 28, 29, 30, 31, 4, 4, 4, 36,
					37 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503,
					503, 503, 503, 503, 503, 503, 503, 503 },
			{ 4, 4, 4, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504,
					504, 504, 4, 4, 4, 504, 504 },
			{ 505, 505, 5, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 6, 505, 505, 505, 505, 505,
					505, 505, 505, 505, 505, 505, 505 },
			{ 1002, 1002, 7, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002,
					1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002, 1002 },
			{ 507, 507, 7, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507,
					507, 507, 507, 507, 507, 507, 507, 507 },
			{ 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508,
					508, 508, 508, 508, 508, 508, 508, 508 },
			{ 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509,
					509, 509, 509, 509, 509, 509, 509, 509 },
			{ 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509,
					509, 509, 509, 509, 509, 509, 509, 509 },
			{ 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509,
					509, 509, 509, 509, 509, 509, 509, 509 },
			{ 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512,
					512, 512, 512, 512, 512, 512, 512, 512 },
			{ 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513,
					513, 513, 513, 513, 513, 513, 513, 513 },
			{ 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514,
					514, 514, 514, 514, 514, 514, 514, 514 },
			{ 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515,
					515, 515, 515, 515, 515, 515, 515, 515 },
			{ 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516,
					516, 516, 516, 516, 516, 516, 516, 516 },
			{ 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517,
					517, 517, 517, 517, 517, 517, 517, 517 },
			{ 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518,
					518, 518, 518, 518, 518, 518, 518, 518 },
			{ 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 20, 21, 509, 509, 509, 509, 509,
					509, 509, 509, 509, 509, 509, 509, 509 },
			{ 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520,
					520, 520, 520, 520, 520, 520, 520, 520 },
			{ 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
					22, 22, 22 },
			{ 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
					22, 22, 22 },
			{ 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 24, 23, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
					22, 22, 22 },
			{ 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524,
					524, 524, 524, 524, 524, 524, 524, 524 },
			{ 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525, 525,
					525, 525, 525, 525, 525, 525, 525, 525 },
			{ 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526, 526,
					526, 526, 526, 526, 526, 526, 526, 526 },
			{ 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527,
					527, 527, 527, 527, 527, 527, 527, 527 },
			{ 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528,
					528, 528, 528, 528, 528, 528, 528, 528 },
			{ 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529,
					529, 529, 529, 529, 529, 529, 529, 529 },
			{ 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530,
					530, 530, 530, 530, 530, 530, 530, 530 },
			{ 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
					1000, 1000, 1000, 1000, 1000, 1000, 1000, 32, 33, 34, 34, 34 },
			{ 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532,
					532, 532, 532, 532, 532, 532, 532, 532 },
			{ 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533,
					533, 533, 533, 533, 533, 533, 533, 533 },
			{ 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534,
					534, 534, 534, 534, 534, 534, 534, 534 },
			{ 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509,
					509, 509, 509, 509, 509, 509, 509, 509 },
			{ 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536,
					536, 536, 536, 536, 536, 536, 536, 536 },
			{ 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537,
					537, 537, 537, 537, 537, 537, 537, 537 }, };

	public void fillList(String text) {
		this.pointer = 0;
		this.characters = text.toCharArray();
	}

	public int forward() {
		return this.pointer++;
	}

	public int inspect() {
		char currentCharacter = this.characters[this.pointer];

		if (Character.isLetter(currentCharacter))
			return 0;
		if (Character.isDigit(currentCharacter))
			return 2;

		switch (currentCharacter) {

		case '_':
			return KeyConstants.UNDER_SCORE;

		case '+':
			return KeyConstants.SUM_OPERATOR;

		case '-':
			return KeyConstants.MINS_OPERATOR;

		case '=':
			return KeyConstants.EQLS_OPERATOR;

		case '!':
			return KeyConstants.NOT_OPERATOR;

		case '|':
			return KeyConstants.OR_OPERATOR;

		case '&':
			return KeyConstants.AMPERSAND;

		case '{':
			return KeyConstants.OPEN_KEY_BRACKET;

		case '}':
			return KeyConstants.CLOSE_KEY_BRACKET;

		case '[':
			return KeyConstants.OPEN_BRACKET;

		case ']':
			return KeyConstants.CLOSE_BRACKET;

		case ';':
			return KeyConstants.SEMICOLON;

		case '/':
			return KeyConstants.SLASH;

		case '*':
			return KeyConstants.ASTERISK;

		case '.':
			return KeyConstants.POINT;

		case '"':
			return KeyConstants.QUOTATION_MARK;

		case '#':
			return KeyConstants.HASH;

		case '(':
			return KeyConstants.OPEN_PARENTHESES;

		case ')':
			return KeyConstants.CLOSE_PARENTESES;

		case '%':
			return KeyConstants.PERCENTAGE;

		case ',':
			return KeyConstants.COMMA;

		case 92:
			return KeyConstants.BACK_SLASH;

		case 'n':
			return KeyConstants.EN;

		case 'r':
			return KeyConstants.ER;

		case 't':
			return KeyConstants.TE;

		case '<':
			return KeyConstants.LESS_THAN;

		case '>':
			return KeyConstants.MORE_THAN;

		default:
			return 0; // return -1 if the character readed is not int the alphabet of the language

		}

	}

	public DataPattern lexerAnalizer() {

		String lex = "";
		DataPattern component;

		try {
			this.state = 0;
			this.chr = inspect();
			this.newState = transitionTable[this.state][chr];
			while (newState < 500) {
				if (this.pointer >= this.characters.length) {
					break;
				}
				char cr = this.characters[this.pointer];
				lex = lex + cr;
				forward();
				this.chr = inspect();
				state = newState;
				newState = transitionTable[this.state][this.chr];
				counter++;

			}
			if (counter == 0) {
				this.forward();
			}

			component = new DataPattern(newState, lex);

		} catch (Exception er) {
			er.printStackTrace();
			component = null;
		}

		return component;

	}

	public String showData() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n=================================================================\n\n");
		sb.append("Starting proccess\n");
		sb.append("=================================================================\n");
		sb.append("Total chars: " + String.valueOf(this.characters.length) + "\n");
		sb.append("------- \t Showing results \t -------\n");

		return sb.toString();

	}

	public String start() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.showData());
		DataPattern component = new DataPattern();

		try {
			do {
				component = this.lexerAnalizer();
				sb.append("Token code: " + component.token + " -- Lexeme: " + component.lexem + "\n");
				System.out.println("Token code: " + component.token + " -- Lexeme: " + component.lexem + "\n");
			} while (component.token != KeyConstants.ERROR_CODE || this.pointer < this.characters.length);

			return sb.toString();

		} catch (Exception er) {
			sb.append(er.getStackTrace());
			return sb.toString();
		}
	}
}
