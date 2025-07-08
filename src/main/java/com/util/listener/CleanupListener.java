package com.util.listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CleanupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// You can log app startup here if needed
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			AbandonedConnectionCleanupThread.checkedShutdown();
			System.out.println("✅ MySQL AbandonedConnectionCleanupThread shutdown successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
