package io.carlease.sales.adapter.out.serialization;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializer;
import io.carlease.sales.application.port.out.ContractPersistencePort;
import io.carlease.sales.domain.Contract;
import io.carlease.sales.domain.ContractNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContractSerializationAdapter implements ContractPersistencePort {

	private static final Logger logger = LoggerFactory.getLogger(ContractSerializationAdapter.class);

	@Override
	public void save(Contract contract) {

		String serializedContract = new GsonBuilder()
				.registerTypeAdapter(java.util.Optional.class,
						(JsonSerializer<Optional<?>>) (opt, t, ctx) ->
								opt == null || opt.isEmpty() ? JsonNull.INSTANCE : ctx.serialize(opt.get()))
				.create()
				.toJson(contract);

		logger.info("Contract as JSON: " + serializedContract);
	}

	@Override
	public boolean exists(ContractNumber number) {
		// TODO implement
		return false;
	}

	@Override
	public Contract with(ContractNumber number) {
		// TODO hier weitermachen mit: https://vaughnvernon.co/?p=942
		return null;
	}

}
