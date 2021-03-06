package com.example.e7440.bankingproject.module.tab.personal.presenter;

import com.example.e7440.bankingproject.R;
import com.example.e7440.bankingproject.connect_api.api.ApiFunction;
import com.example.e7440.bankingproject.connect_api.api.ApiStatus;
import com.example.e7440.bankingproject.connect_api.responses.ResponseTool;
import com.example.e7440.bankingproject.module.base.BasePresenter;
import com.example.e7440.bankingproject.module.tab.personal.PersonalGeneral;

public class PersonalPresenterImpl extends BasePresenter<PersonalGeneral.PersonalView> implements PersonalGeneral.PersonalPresenter {
    public PersonalPresenterImpl(PersonalGeneral.PersonalView mView) {
        super(mView);
    }

    @Override
    public void onResponseListener(ApiFunction apiFunction, ApiStatus statusId, Object object) {
        switch (apiFunction) {
            case GET_TAB_PERSONAL: {
                switch (statusId) {
                    case CALL_API_RESULT_SUCCESS: {
                        ResponseTool responseTool = (ResponseTool) object;
                        getmView().fetchTab(responseTool.getTab());
                        break;
                    }
                    case CALL_API_RESULT_FAILURE: {
                        getmView().showDialogError(R.string.error_002);
                        break;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void getTab(String url) {
        if (getmView().isNetworkConnect()) {
            mApiMethod.getTabPersonal(url);
        } else {
            getmView().networkConnectError();
        }
    }
}
