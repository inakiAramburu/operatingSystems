public class MySemafore {
    
    private int tokens;

    public MySemafore(int i){
        this.tokens =i;
    }

    public synchronized void acquire() throws InterruptedException{
        while(this.tokens == 0){
            this.wait();
            if(this.tokens==0)
                System.out.println("supurious wakeup");    
        }

        this.tokens--;
    }

    public synchronized void release(){
        if (this.tokens ==0){
        this.notify();
        }
        this.tokens++;
    }

}
