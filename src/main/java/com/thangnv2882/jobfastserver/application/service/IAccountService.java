package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.account.ChangeAvatarInput;
import com.thangnv2882.jobfastserver.application.input.account.GetAccountByEmailInput;
import com.thangnv2882.jobfastserver.application.input.account.UpdateAccountInput;
import com.thangnv2882.jobfastserver.application.input.commons.FindAccountInput;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.output.GetAccountOutput;
import com.thangnv2882.jobfastserver.application.output.GetListAccountOutput;
import com.thangnv2882.jobfastserver.application.output.UploadFileOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;

import java.io.IOException;

public interface IAccountService {

  GetListAccountOutput findAllAccount(FindAccountInput findAccountInput);

  GetAccountOutput findAccountById(Input input);

  GetAccountOutput findAccountByEmail(GetAccountByEmailInput getAccountByEmailInput);

  Output updateAccount(UpdateAccountInput input);

  Output deleteAccount(Input input);

  UploadFileOutput changeAvatar(ChangeAvatarInput input) throws IOException;

}
