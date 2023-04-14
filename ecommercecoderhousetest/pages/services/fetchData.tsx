import axios,{AxiosResponse} from 'axios';

export interface Client {
	id: Number,
	name: String,
	lastName: String,
	docNumber: String
}

export async function fetchData (): Promise<Client[]> {
	try {
		const response: AxiosResponse = await axios.get('http://localhost:8080/api/client/');
		console.log(response);
		return response.data;
	} catch (error) {
		console.error(error);
		return[];		
	}
}