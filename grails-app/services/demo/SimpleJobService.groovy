package demo

import com.agileorbit.schwartz.SchwartzJob
import com.agileorbit.schwartz.builder.MisfireHandling
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.quartz.DateBuilder
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException

@CompileStatic
@Slf4j
class SimpleJobService implements SchwartzJob {

    void execute(JobExecutionContext context) throws JobExecutionException {
        println ' --- This message should not be displayed when the application starts ---'
    }

    void buildTriggers() {
        triggers << factory('Sample job')
            .startAt(DateBuilder.todayAt(4, 30, 0))
//            .startAt(DateBuilder.newDate().atHourMinuteAndSecond(4, 30, 0).build())
//            .startAt(DateBuilder.newDate().dateOf(4, 30, 0))
            .intervalInDays(1)
            .misfireHandling(MisfireHandling.IgnoreMisfires)
            .build()
    }
}
