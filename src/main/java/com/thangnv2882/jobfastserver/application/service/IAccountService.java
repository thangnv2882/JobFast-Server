package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.account.*;
import com.thangnv2882.jobfastserver.application.input.commons.FindAccountInput;
import com.thangnv2882.jobfastserver.application.output.GetAccountOutput;
import com.thangnv2882.jobfastserver.application.output.GetListAccountOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.UploadFileOutput;

import java.io.IOException;

public interface IAccountService {

  GetListAccountOutput findAllAccount(FindAccountInput findAccountInput);

  GetAccountOutput getAccountById(GetAccountByIdInput getAccountByIdInput);

  GetAccountOutput getAccountByEmail(GetAccountByEmailInput getAccountByEmailInput);

  Output updateAccount(UpdateAccountInput input);

  Output deleteAccount(DeleteAccountInput input);

  UploadFileOutput changeAvatar(ChangeAvatarInput input) throws IOException;

}
