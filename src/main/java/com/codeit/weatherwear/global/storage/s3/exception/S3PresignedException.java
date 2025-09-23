package com.codeit.weatherwear.global.storage.s3.exception;

import com.codeit.weatherwear.global.exception.CustomException;
import com.codeit.weatherwear.global.exception.ErrorCode;

public class S3PresignedException extends CustomException {

  public S3PresignedException() {
    super(ErrorCode.PRESIGNED_URL_GENERATION_FAILED);
  }
}
