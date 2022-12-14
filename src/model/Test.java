package model;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Test implements Serializable {

	private static final long serialVersionUID = -795931782953363826L; // auto-generated by serializable
	private ArrayList<Question> testQuestions;
	private int numOfQuestions;
	private int wantedNumOfQuestions;

	public Test(int num) {
		testQuestions = new ArrayList<Question>();
		numOfQuestions = 0;
		wantedNumOfQuestions = num;
	}

	public Test(Test test) {
		this.testQuestions = test.getTestQuestions();
		this.numOfQuestions = test.getNumOfQuestions();
		this.wantedNumOfQuestions = test.getWantedNumOfQuestions();
	}

	public ArrayList<Question> getTestQuestions() {
		return testQuestions;
	}

	public int getNumOfQuestions() {
		return numOfQuestions;
	}

	/* add a question to the array */
	public boolean addQuestion(Question question) throws IndexOutOfBoundsException {
		if (numOfQuestions == wantedNumOfQuestions) // check if array is full
			return false;
		for (int i = 0; i < numOfQuestions; i++) {
			if (testQuestions.get(i).equals(question))
				return false;
		}
		testQuestions.add(question);
		numOfQuestions++;
		return true;
	}

	public String toStringWithAnswer() throws IndexOutOfBoundsException { // with full answers
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numOfQuestions; i++) {
			sb.append((i + 1) + ". " + testQuestions.get(i).toStringForTest() + "\n\n\t The correct answer is: ");
			if (testQuestions.get(i).getClass().equals(MultiChoiceQuestion.class)) {
				for (int j = 0; j < ((MultiChoiceQuestion) testQuestions.get(i)).getNumOfAnswers(); j++) {
					if (((MultiChoiceQuestion) testQuestions.get(i)).getAnswers().get(j).getIsTrue()) {
						sb.append(((MultiChoiceQuestion) testQuestions.get(i)).getAnswers().get(j).getAnswer());
						j = ((MultiChoiceQuestion) testQuestions.get(i)).getNumOfAnswers();
					}
				}
			} else {
				sb.append(((OpenQuestion) testQuestions.get(i)).getAnswer().getAnswer());
			}

			sb.append("\n ------------------------------ \n");
		}
		return sb.toString();
	}

	/* bubble sort by answer length */
	public void sortQuestions() throws IndexOutOfBoundsException {
		for (int i = numOfQuestions; i > 1; i--)
			for (int j = 1; j < i; j++)
				if (testQuestions.get(j - 1).getAnswerLength() > testQuestions.get(j).getAnswerLength())
					swap(j, j - 1);
	}

	/* swap for bubble sort */
	public void swap(int i, int j) throws IndexOutOfBoundsException {
		Question tmp = testQuestions.get(i);
		testQuestions.set(i, testQuestions.get(j));
		testQuestions.set(j, tmp);
	}

	public int getWantedNumOfQuestions() {
		return wantedNumOfQuestions;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		return Objects.equals(testQuestions, other.testQuestions);
	}

	@Override
	public String toString() throws IndexOutOfBoundsException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numOfQuestions; i++) {
			sb.append((i + 1) + ". " + testQuestions.get(i).toStringForTest() + "\n ------------------------------ \n");
		}
		return sb.toString();
	}

	public void save(PrintWriter pw) {
		pw.print(toString());
	}

	public void saveWithAnswers(PrintWriter pw) {
		pw.print(toStringWithAnswer());
	}

}
