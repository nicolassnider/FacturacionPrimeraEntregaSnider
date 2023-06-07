import { useEffect, useState } from "react";
import { Client, fetchClients } from "../services/fetchData";
import { ModalInvoices } from "./ModalInvoices";

interface ClientProps {
	id: number;
	name: string;
	lastName: string;
	docNumber: string;
}
export function Clients({  }: ClientProps) {
	const [clients, setClients] = useState<Client[]>([]);
	const [selectedClient, setSelectedClient] = useState<Client | null>(null);
	const [isOpen, setIsOpen] = useState(false);

	useEffect(() => {
		async function getData() {
			const result = await fetchClients();
			setClients(result);
		}
		getData();
	}, []);

	const handleModalClick = (client: Client) => {
		console.log('client', client);
		setSelectedClient(client);
		setIsOpen(true);
	};

	const onClose = () => {
		setSelectedClient(null);
		setIsOpen(false);
	};

	return (
		<div className="bg-dark-bg min-h-screen flex items-center justify-center">
			<div className="grid grid-cols-4 gap-4">
				<ModalInvoices
					clientId={selectedClient}
					isOpen={isOpen}
					onClose={onClose}
					children={undefined}
				></ModalInvoices>
				<div className="col-span-4">
					<h1 className="text-3xl font-bold text-neutral-800 dark:text-neutral-50">
						Welcome to the Dashboard
					</h1>
					<p className="text-neutral-600 dark:text-neutral-200">
						Here you can see the most important information
					</p>
				</div>
				{clients.map((client: Client) => (
					<div
						key={client.id}
						onClick={() => handleModalClick(client.id)}
						className=" box block mt-1 max-w-[22rem] rounded-lg bg-white p-6 shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700"
					>
						<h5 className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50">
							{client.name} {client.lastName}
						</h5>
						<p className="mb-4 text-base text-neutral-600 dark:text-neutral-200">
							{client.docNumber}
						</p>
					</div>
				))}
			</div>
		</div>
	);
}
