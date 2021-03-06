package iyunu.NewTLOL.base.net;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetService {
	private Logger logger = LoggerFactory.getLogger(NetService.class);
	private int port;
	private ChannelPipelineFactory channelPipelineFactory;
	private ChannelBufferFactory channelBufferFactory;

	/**
	 * 创建网络服务
	 * 
	 * @param port
	 *            监听端口
	 * @param channelPipelineFactory
	 *            {@link ChannelPipelineFactory}
	 * @param channelBufferFactory
	 *            {@link ChannelBufferFactory}
	 */
	public NetService(int port, ChannelPipelineFactory channelPipelineFactory, ChannelBufferFactory channelBufferFactory) {
		this.port = port;
		this.channelPipelineFactory = channelPipelineFactory;
		this.channelBufferFactory = channelBufferFactory;
	}

	/**
	 * 开启网络服务
	 */
	public void run() {
		// Configure the server.
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));

		// Set up the pipeline factory.
		bootstrap.setPipelineFactory(channelPipelineFactory);

		bootstrap.setOption("child.bufferFactory", channelBufferFactory);
		// Bind and start to accept incoming connections.
		bootstrap.bind(new InetSocketAddress(port));
		logger.info("server started on port " + port + "...");
	}
}
