package panel;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class SudokuTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	
	private final int LIMIT;

	public SudokuTextField(int limit) {
        super();
        this.LIMIT = limit;
    }

	protected Document createDefaultModel() {
        return new LimitDocument();
    }

    private class LimitDocument extends PlainDocument {
    	
		private static final long serialVersionUID = 1L;

		@Override
        public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException{
            if (str == null) return;

            if ((getLength() + str.length()) <= LIMIT) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
