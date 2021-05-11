public class LoadIndInstruction extends Instruction{
    int register;

    public LoadIndInstruction(int register) {
        this.register = register;
    }

    @Override
    void run() {
        Main.accu = Main.memory[register];
        Main.pc = Main.pc + 1;
        
    }
}
