package io.carlease.sales.adapter.out.serialization;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializer;
import io.carlease.sales.domain.Contract;
import io.carlease.sales.domain.ContractNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class ContractSerializationAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ContractSerializationAdapter.class);


	public void save(Contract contract) {

		String serializedContract = new GsonBuilder()
				.registerTypeAdapter(java.util.Optional.class,
						(JsonSerializer<Optional<?>>) (opt, t, ctx) ->
								opt == null || opt.isEmpty() ? JsonNull.INSTANCE : ctx.serialize(opt.get()))
				.create()
				.toJson(contract);

		logger.info("Contract as JSON: " + serializedContract);

		// TODO: saving to file

	}


	public boolean exists(ContractNumber number) {
		return false;
	}


	public Contract with(ContractNumber number) {
		return null;
	}

}
