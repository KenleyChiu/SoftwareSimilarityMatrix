package Backend;

public class ProgramLine {
    private String line;
    private boolean compared;

    public ProgramLine(String line,boolean compared){
        this.line=line;
        this.compared=compared;
    }

    public void setBoolean(boolean compared){
        this.compared=compared;
    }

    public String getLine(){
        return this.line;
    }

    public boolean getBoolean(){
        return this.compared;
    }

}
