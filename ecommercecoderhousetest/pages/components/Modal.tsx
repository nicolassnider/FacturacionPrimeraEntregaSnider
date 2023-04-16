import { ReactNode, useEffect, useState } from 'react';
import { Invoice, fetchInvoices } from '../services/fetchData';
import Card from './InvoiceCard';

interface ModalProps {
	isOpen: boolean;
	onClose: () => void;
	clientId: Number | undefined;
	children: ReactNode;
}

export function Modal({ isOpen, onClose, clientId, children }: ModalProps) {
	const [invoices, setInvoices] = useState<Invoice[]>([]);

	useEffect(() => {
		async function getData() {
			console.log('clientId', clientId);
			if (clientId === undefined) return;
			const result = await fetchInvoices(clientId);
			setInvoices(result);
		}
		getData();
	}, [clientId]);

	if (!isOpen) {
		return null;
	}

	return (
		<div className="fixed z-50 inset-0 overflow-y-auto">
			<div className="flex items-center justify-center min-h-screen">
				<div className="fixed inset-0 bg-gray-900 bg-opacity-75"></div>
				<div className="relative bg-gray-800 rounded-lg w-1/2 text-white">
					<button
						className="absolute top-0 right-0 m-4 text-gray-300 hover:text-gray-100"
						onClick={onClose}
					>
						X
					</button>

					<div className="p-6">{children}</div>

					{invoices.map((invoice: Invoice) => (
						
						<Card
							key={invoice.id}
							title={invoice.id}
							createdAt={invoice.createdAt}
							total={invoice.total}
							invoice = {invoice}
						/>
					))}
				</div>
			</div>
		</div>
	);
}
