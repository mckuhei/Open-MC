package tcgstudio2022.openMC.util.event;



public class TimerQueueEvent {
    public TimerEvent event;
    public long lastUpdateTime;
    public long remainingTime;
    public boolean isPeriod;
    public long latency;
}
