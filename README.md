nnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - com.mchange.v2.async.ThreadPoolAsynchronousRunner$DeadlockDetector@15a4ecd -- APPARENT DEADLOCK!!! Creating emergency threads for unassigned pending tasks!
04:27:00 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - com.mchange.v2.async.ThreadPoolAsynchronousRunner$DeadlockDetector@15a4ecd -- APPARENT DEADLOCK!!! Complete Status:
        Managed Threads: 3
        Active Threads: 3
        Active Tasks:
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@7f24b3af
                        on thread: C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#0
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@6531fe3b
                        on thread: C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#1
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@2e8f45e2
                        on thread: C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#2
        Pending Tasks:
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@4e7c6a98
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@149f0133
Pool thread stack traces:
        Thread[C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#0,5,main]
                java.base@11.0.22/sun.nio.ch.Net.connect0(Native Method)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:483)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:472)
                java.base@11.0.22/sun.nio.ch.SocketChannelImpl.connect(SocketChannelImpl.java:692)
                java.base@11.0.22/java.nio.channels.SocketChannel.open(SocketChannel.java:194)
                oracle.net.nt.TimeoutSocketChannel.connect(TimeoutSocketChannel.java:184)
                oracle.net.nt.TimeoutSocketChannel.<init>(TimeoutSocketChannel.java:158)
                oracle.net.nt.TcpNTAdapter.establishSocket(TcpNTAdapter.java:380)
                oracle.net.nt.TcpNTAdapter.doLocalDNSLookupConnect(TcpNTAdapter.java:303)
                oracle.net.nt.TcpNTAdapter.connect(TcpNTAdapter.java:265)
                oracle.net.nt.ConnOption.connect(ConnOption.java:229)
                oracle.net.nt.ConnStrategy.executeConnOption(ConnStrategy.java:814)
                oracle.net.nt.ConnStrategy.execute(ConnStrategy.java:555)
                oracle.net.resolver.AddrResolution.resolveAndExecute(AddrResolution.java:565)
                oracle.net.ns.NSProtocol.establishConnection(NSProtocol.java:937)
                oracle.net.ns.NSProtocol.connect(NSProtocol.java:346)
                oracle.jdbc.driver.T4CConnection.connect(T4CConnection.java:1967)
                oracle.jdbc.driver.T4CConnection.logon(T4CConnection.java:640)
                oracle.jdbc.driver.PhysicalConnection.connect(PhysicalConnection.java:1032)
                oracle.jdbc.driver.T4CDriverExtension.getConnection(T4CDriverExtension.java:90)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:681)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:602)
                com.mchange.v2.c3p0.DriverManagerDataSource.getConnection(DriverManagerDataSource.java:175)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:220)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:206)
                com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:203)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquire(BasicResourcePool.java:1176)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquireAndDecrementPendingAcquiresWithinLockOnSuccess(BasicResourcePool.java:1163)
                com.mchange.v2.resourcepool.BasicResourcePool.access$700(BasicResourcePool.java:44)
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1908)
                com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:696)
        Thread[C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#1,5,main]
                java.base@11.0.22/sun.nio.ch.Net.connect0(Native Method)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:483)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:472)
                java.base@11.0.22/sun.nio.ch.SocketChannelImpl.connect(SocketChannelImpl.java:692)
                java.base@11.0.22/java.nio.channels.SocketChannel.open(SocketChannel.java:194)
                oracle.net.nt.TimeoutSocketChannel.connect(TimeoutSocketChannel.java:184)
                oracle.net.nt.TimeoutSocketChannel.<init>(TimeoutSocketChannel.java:158)
                oracle.net.nt.TcpNTAdapter.establishSocket(TcpNTAdapter.java:380)
                oracle.net.nt.TcpNTAdapter.doLocalDNSLookupConnect(TcpNTAdapter.java:303)
                oracle.net.nt.TcpNTAdapter.connect(TcpNTAdapter.java:265)
                oracle.net.nt.ConnOption.connect(ConnOption.java:229)
                oracle.net.nt.ConnStrategy.executeConnOption(ConnStrategy.java:814)
                oracle.net.nt.ConnStrategy.execute(ConnStrategy.java:555)
                oracle.net.resolver.AddrResolution.resolveAndExecute(AddrResolution.java:565)
                oracle.net.ns.NSProtocol.establishConnection(NSProtocol.java:937)
                oracle.net.ns.NSProtocol.connect(NSProtocol.java:346)
                oracle.jdbc.driver.T4CConnection.connect(T4CConnection.java:1967)
                oracle.jdbc.driver.T4CConnection.logon(T4CConnection.java:640)
                oracle.jdbc.driver.PhysicalConnection.connect(PhysicalConnection.java:1032)
                oracle.jdbc.driver.T4CDriverExtension.getConnection(T4CDriverExtension.java:90)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:681)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:602)
                com.mchange.v2.c3p0.DriverManagerDataSource.getConnection(DriverManagerDataSource.java:175)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:220)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:206)
                com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:203)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquire(BasicResourcePool.java:1176)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquireAndDecrementPendingAcquiresWithinLockOnSuccess(BasicResourcePool.java:1163)
                com.mchange.v2.resourcepool.BasicResourcePool.access$700(BasicResourcePool.java:44)
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1908)
                com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:696)
        Thread[C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#2,5,main]
                java.base@11.0.22/sun.nio.ch.Net.connect0(Native Method)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:483)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:472)
                java.base@11.0.22/sun.nio.ch.SocketChannelImpl.connect(SocketChannelImpl.java:692)
                java.base@11.0.22/java.nio.channels.SocketChannel.open(SocketChannel.java:194)
                oracle.net.nt.TimeoutSocketChannel.connect(TimeoutSocketChannel.java:184)
                oracle.net.nt.TimeoutSocketChannel.<init>(TimeoutSocketChannel.java:158)
                oracle.net.nt.TcpNTAdapter.establishSocket(TcpNTAdapter.java:380)
                oracle.net.nt.TcpNTAdapter.doLocalDNSLookupConnect(TcpNTAdapter.java:303)
                oracle.net.nt.TcpNTAdapter.connect(TcpNTAdapter.java:265)
                oracle.net.nt.ConnOption.connect(ConnOption.java:229)
                oracle.net.nt.ConnStrategy.executeConnOption(ConnStrategy.java:814)
                oracle.net.nt.ConnStrategy.execute(ConnStrategy.java:555)
                oracle.net.resolver.AddrResolution.resolveAndExecute(AddrResolution.java:565)
                oracle.net.ns.NSProtocol.establishConnection(NSProtocol.java:937)
                oracle.net.ns.NSProtocol.connect(NSProtocol.java:346)
                oracle.jdbc.driver.T4CConnection.connect(T4CConnection.java:1967)
                oracle.jdbc.driver.T4CConnection.logon(T4CConnection.java:640)
                oracle.jdbc.driver.PhysicalConnection.connect(PhysicalConnection.java:1032)
                oracle.jdbc.driver.T4CDriverExtension.getConnection(T4CDriverExtension.java:90)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:681)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:602)
                com.mchange.v2.c3p0.DriverManagerDataSource.getConnection(DriverManagerDataSource.java:175)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:220)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:206)
                com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:203)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquire(BasicResourcePool.java:1176)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquireAndDecrementPendingAcquiresWithinLockOnSuccess(BasicResourcePool.java:1163)
                com.mchange.v2.resourcepool.BasicResourcePool.access$700(BasicResourcePool.java:44)
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1908)
                com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:696)

04:28:15 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - Task com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@7f24b3af (in deadlocked PoolThread) failed to complete in maximum time 60000ms. Trying interrupt().
04:28:15 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - Task com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@6531fe3b (in deadlocked PoolThread) failed to complete in maximum time 60000ms. Trying interrupt().
04:28:15 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - Task com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@2e8f45e2 (in deadlocked PoolThread) failed to complete in maximum time 60000ms. Trying interrupt().
04:31:30 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - com.mchange.v2.async.ThreadPoolAsynchronousRunner$DeadlockDetector@15a4ecd -- APPARENT DEADLOCK!!! Creating emergency threads for unassigned pending tasks!
04:31:30 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - com.mchange.v2.async.ThreadPoolAsynchronousRunner$DeadlockDetector@15a4ecd -- APPARENT DEADLOCK!!! Complete Status:
        Managed Threads: 3
        Active Threads: 3
        Active Tasks:
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@7cdc36e5
                        on thread: C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#1
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@3cbba9fd
                        on thread: C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#2
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@17489d3a
                        on thread: C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#0
        Pending Tasks:
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@d269612
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@10d0e24d
Pool thread stack traces:
        Thread[C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#1,5,main]
                java.base@11.0.22/sun.nio.ch.Net.connect0(Native Method)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:483)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:472)
                java.base@11.0.22/sun.nio.ch.SocketChannelImpl.connect(SocketChannelImpl.java:692)
                java.base@11.0.22/java.nio.channels.SocketChannel.open(SocketChannel.java:194)
                oracle.net.nt.TimeoutSocketChannel.connect(TimeoutSocketChannel.java:184)
                oracle.net.nt.TimeoutSocketChannel.<init>(TimeoutSocketChannel.java:158)
                oracle.net.nt.TcpNTAdapter.establishSocket(TcpNTAdapter.java:380)
                oracle.net.nt.TcpNTAdapter.doLocalDNSLookupConnect(TcpNTAdapter.java:303)
                oracle.net.nt.TcpNTAdapter.connect(TcpNTAdapter.java:265)
                oracle.net.nt.ConnOption.connect(ConnOption.java:229)
                oracle.net.nt.ConnStrategy.executeConnOption(ConnStrategy.java:814)
                oracle.net.nt.ConnStrategy.execute(ConnStrategy.java:555)
                oracle.net.resolver.AddrResolution.resolveAndExecute(AddrResolution.java:565)
                oracle.net.ns.NSProtocol.establishConnection(NSProtocol.java:937)
                oracle.net.ns.NSProtocol.connect(NSProtocol.java:346)
                oracle.jdbc.driver.T4CConnection.connect(T4CConnection.java:1967)
                oracle.jdbc.driver.T4CConnection.logon(T4CConnection.java:640)
                oracle.jdbc.driver.PhysicalConnection.connect(PhysicalConnection.java:1032)
                oracle.jdbc.driver.T4CDriverExtension.getConnection(T4CDriverExtension.java:90)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:681)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:602)
                com.mchange.v2.c3p0.DriverManagerDataSource.getConnection(DriverManagerDataSource.java:175)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:220)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:206)
                com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:203)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquire(BasicResourcePool.java:1176)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquireAndDecrementPendingAcquiresWithinLockOnSuccess(BasicResourcePool.java:1163)
                com.mchange.v2.resourcepool.BasicResourcePool.access$700(BasicResourcePool.java:44)
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1908)
                com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:696)
        Thread[C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#2,5,main]
                java.base@11.0.22/sun.nio.ch.Net.connect0(Native Method)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:483)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:472)
                java.base@11.0.22/sun.nio.ch.SocketChannelImpl.connect(SocketChannelImpl.java:692)
                java.base@11.0.22/java.nio.channels.SocketChannel.open(SocketChannel.java:194)
                oracle.net.nt.TimeoutSocketChannel.connect(TimeoutSocketChannel.java:184)
                oracle.net.nt.TimeoutSocketChannel.<init>(TimeoutSocketChannel.java:158)
                oracle.net.nt.TcpNTAdapter.establishSocket(TcpNTAdapter.java:380)
                oracle.net.nt.TcpNTAdapter.doLocalDNSLookupConnect(TcpNTAdapter.java:303)
                oracle.net.nt.TcpNTAdapter.connect(TcpNTAdapter.java:265)
                oracle.net.nt.ConnOption.connect(ConnOption.java:229)
                oracle.net.nt.ConnStrategy.executeConnOption(ConnStrategy.java:814)
                oracle.net.nt.ConnStrategy.execute(ConnStrategy.java:555)
                oracle.net.resolver.AddrResolution.resolveAndExecute(AddrResolution.java:565)
                oracle.net.ns.NSProtocol.establishConnection(NSProtocol.java:937)
                oracle.net.ns.NSProtocol.connect(NSProtocol.java:346)
                oracle.jdbc.driver.T4CConnection.connect(T4CConnection.java:1967)
                oracle.jdbc.driver.T4CConnection.logon(T4CConnection.java:640)
                oracle.jdbc.driver.PhysicalConnection.connect(PhysicalConnection.java:1032)
                oracle.jdbc.driver.T4CDriverExtension.getConnection(T4CDriverExtension.java:90)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:681)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:602)
                com.mchange.v2.c3p0.DriverManagerDataSource.getConnection(DriverManagerDataSource.java:175)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:220)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:206)
                com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:203)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquire(BasicResourcePool.java:1176)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquireAndDecrementPendingAcquiresWithinLockOnSuccess(BasicResourcePool.java:1163)
                com.mchange.v2.resourcepool.BasicResourcePool.access$700(BasicResourcePool.java:44)
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1908)
                com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:696)
        Thread[C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-HelperThread-#0,5,main]
                java.base@11.0.22/sun.nio.ch.Net.connect0(Native Method)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:483)
                java.base@11.0.22/sun.nio.ch.Net.connect(Net.java:472)
                java.base@11.0.22/sun.nio.ch.SocketChannelImpl.connect(SocketChannelImpl.java:692)
                java.base@11.0.22/java.nio.channels.SocketChannel.open(SocketChannel.java:194)
                oracle.net.nt.TimeoutSocketChannel.connect(TimeoutSocketChannel.java:184)
                oracle.net.nt.TimeoutSocketChannel.<init>(TimeoutSocketChannel.java:158)
                oracle.net.nt.TcpNTAdapter.establishSocket(TcpNTAdapter.java:380)
                oracle.net.nt.TcpNTAdapter.doLocalDNSLookupConnect(TcpNTAdapter.java:303)
                oracle.net.nt.TcpNTAdapter.connect(TcpNTAdapter.java:265)
                oracle.net.nt.ConnOption.connect(ConnOption.java:229)
                oracle.net.nt.ConnStrategy.executeConnOption(ConnStrategy.java:814)
                oracle.net.nt.ConnStrategy.execute(ConnStrategy.java:555)
                oracle.net.resolver.AddrResolution.resolveAndExecute(AddrResolution.java:565)
                oracle.net.ns.NSProtocol.establishConnection(NSProtocol.java:937)
                oracle.net.ns.NSProtocol.connect(NSProtocol.java:346)
                oracle.jdbc.driver.T4CConnection.connect(T4CConnection.java:1967)
                oracle.jdbc.driver.T4CConnection.logon(T4CConnection.java:640)
                oracle.jdbc.driver.PhysicalConnection.connect(PhysicalConnection.java:1032)
                oracle.jdbc.driver.T4CDriverExtension.getConnection(T4CDriverExtension.java:90)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:681)
                oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:602)
                com.mchange.v2.c3p0.DriverManagerDataSource.getConnection(DriverManagerDataSource.java:175)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:220)
                com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:206)
                com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:203)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquire(BasicResourcePool.java:1176)
                com.mchange.v2.resourcepool.BasicResourcePool.doAcquireAndDecrementPendingAcquiresWithinLockOnSuccess(BasicResourcePool.java:1163)
                com.mchange.v2.resourcepool.BasicResourcePool.access$700(BasicResourcePool.java:44)
                com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1908)
                com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:696)

04:32:30 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - Task com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@7cdc36e5 (in deadlocked PoolThread) failed to complete in maximum time 60000ms. Trying interrupt().
04:32:30 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - Task com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@3cbba9fd (in deadlocked PoolThread) failed to complete in maximum time 60000ms. Trying interrupt().
04:32:30 [C3P0PooledConnectionPoolManager[identityToken->2zqbe0b21v5l3gg18ukgc2|5a20452e]-AdminTaskTimer] WARN  com.mchange.v2.async.ThreadPoolAsynchronousRunner  - Task com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@17489d3a (in deadlocked PoolThread) failed to complete in maximum time 60000ms. Trying interrupt().
04:32:34 [pool-1-thread-1] WARN  com.activiti.service.ActivitiEndpointLicenseService  - Error while calling Activiti endpoint: Activiti app - assuming valid license: internal-cpcwms22-prod-WmsInternalSrvELB-337932776.us-east-1.elb.amazonaws.com: Name or service not known

