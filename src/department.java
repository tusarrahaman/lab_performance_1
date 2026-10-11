public enum department {
    CSE, EEE, BBA;


    private int aPlus = 0, a = 0, bPlus = 0, b = 0, cPlus = 0, c = 0, dPlus = 0, d = 0, f = 0;


    public synchronized void countGrade(String grade) {
        if (grade.equals("A+")) aPlus++;
        else if (grade.equals("A")) a++;
        else if (grade.equals("B+")) bPlus++;
        else if (grade.equals("B")) b++;
        else if (grade.equals("C+")) cPlus++;
        else if (grade.equals("C")) c++;
        else if (grade.equals("D+")) dPlus++;
        else if (grade.equals("D")) d++;
        else if (grade.equals("F")) f++;
    }

    public String getGradeSummary() {
        return name() + ": A+=" + aPlus + " , A=" + a + " , B+=" + bPlus +
                ", B=" + b + ", C+=" + cPlus + " ,C=" + c + ",D+ =" + dPlus + " , D=" + d + " , F=" + f;
    }
}