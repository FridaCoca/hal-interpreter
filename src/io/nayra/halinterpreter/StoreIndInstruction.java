public class StoreIndInstruction extends Instruction {
    int register;

    public StoreIndInstruction(int register) {
        this.register = register;
    }

    @Override
    void run() {
        Main.memory[register] = Main.accu;
        Main.pc = Main.pc + 1;
        
    }
}

