package com.iwami.iwami.app.service;

import com.iwami.iwami.app.model.Task;
import com.iwami.iwami.app.model.Wami;

public interface WamiService {

	Task getPrize(long taskid);

	boolean getIdStatus(long taskid);

	boolean getUseridStatus(int userid);

	boolean statusUplod(Wami wami);

}
