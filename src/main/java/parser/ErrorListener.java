package parser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ErrorListener extends BaseErrorListener {

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		if (e == null || e.getOffendingToken() == null) {
			throw new RuntimeException(
					"Syntax error at line " + line + ", character " + charPositionInLine + ": " + msg);
		} else {
			throw new RuntimeException("Syntax error at line " + e.getOffendingToken().getLine() + ", character "
					+ e.getOffendingToken().getCharPositionInLine() + ": " + e.getMessage(), e);
		}

	}

}
