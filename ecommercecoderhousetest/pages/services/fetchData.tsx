import axios,{AxiosResponse} from 'axios';

export interface Client {
	id: Number,
	name: String,
	lastName: String,
	docNumber: String
}
export interface Invoice {
	id: Number,
	createdAt: Date,
	total: Number
}

export async function fetchClients (): Promise<Client[]> {
	try {
		const response: AxiosResponse = await axios.get('http://localhost:8080/api/client/');
		console.log(response);
		return response.data;
	} catch (error) {
		console.error(error);
		return[];		
	}
}

export async function fetchInvoices (clientId:Number): Promise<Invoice[]> {
	console.log('clientId', clientId)
	try {
		if(clientId === undefined) return [];	
		const response: AxiosResponse = await axios.get( `http://localhost:8080/api/invoice/client/${clientId}`);
		console.log(response);
		return response.data;
	} catch (error) {
		console.error(error);
		return[];		
	}
}