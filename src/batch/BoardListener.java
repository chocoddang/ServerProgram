package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Application Lifecycle Listener implementation class BoardListener
 *
 */
@WebListener
public class BoardListener implements ServletContextListener {

	private Scheduler scheduler;
	
    /**
     * Default constructor. 
     */
    public BoardListener() {
    	 try {
         	scheduler = new StdSchedulerFactory().getScheduler();
         } catch (Exception e) {
         	e.printStackTrace();
         }
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	 try {
        	 if (scheduler != null) {
        		 scheduler.shutdown();
        	 }
         } catch(Exception e) {
        	 e.printStackTrace();
         }
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	try {
        	
        	JobDetail job = JobBuilder.newJob(SelectBoardMaxHitJob.class)
        			.withIdentity("job","group")
        			.build();
        	
        	Trigger trigger = TriggerBuilder.newTrigger()
        			.withIdentity("trigger", "group")
        			.withSchedule(CronScheduleBuilder.cronSchedule(""))  // 크롬메이커에서 찾기
        			.build();
        	// 3) 스캐쥴러 job, trigger 등록
        	scheduler.scheduleJob(job, trigger);
        	// 4) 스캐쥴러 실행 시작
        	scheduler.start();
   
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
}
