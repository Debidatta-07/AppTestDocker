package com.cavisson.ata.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerMDCWork
{

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public void dynamic()
  {

    log.debug("debug dynamic level log");
    log.info("info dynamic level log");
    log.error("error dynamic level log");

    log.info("another info log with {}, {} and {} arguments", 1, "2", 3.0);

    log.trace(" dynamic trace dynamic level log");

    log.warn(" warning dynamic level log");
  }

  public void rx()
  {
    log.debug("debug level log RX");

    log.info("info level log RX");

    log.error("error level log RX");

    log.info("another info log with {}, {} and {} arguments", 1, "2", 3.0);

    log.trace(" trace level log RX");

    log.warn(" warning level log RX");

  }
}